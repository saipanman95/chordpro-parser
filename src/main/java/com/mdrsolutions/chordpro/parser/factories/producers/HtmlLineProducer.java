package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.enums.HtmlAttribute;
import com.mdrsolutions.chordpro.parser.enums.HtmlValueKeyPair;
import com.mdrsolutions.chordpro.parser.enums.HtmlWrapper;
import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.HtmlSongLine;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author mrodgers
 */
public class HtmlLineProducer implements Producer<HtmlSongLine, RawSongLine> {

    private static final String EMPTY_SPACE = "";
    private static final String SINGLE_SPACE = " ";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";
    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]"; 
private final Properties prop = new Properties();

    private void loadProperties() {
        try {
            prop.load(new FileInputStream("styles.properties"));
        } catch (FileNotFoundException ex) {
            System.out.println("loadProperties() - logging stuff = " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("loadProperties() - logging stuff = " + ex.getLocalizedMessage());
        }
    }
    public HtmlLineProducer(final HtmlMetaTagDirectiveBuilder directiveFactory) {
        this.directiveFactory = directiveFactory;
        loadProperties();
    }

    private final HtmlMetaTagDirectiveBuilder directiveFactory;
    
    @Override
    public HtmlSongLine produce(RawSongLine rawSongLine) {

        List<ChordLocation> chordLocales = rawSongLine.getChordLocations();
        String songLine = rawSongLine.getSong();

        StringBuilder chordLocaleBldr = new StringBuilder();
        Integer previousColumnIndex = 0;

        for (ChordLocation chordLocation : chordLocales) {

            Integer columnIndex = chordLocation.getColumnIndex();
            columnIndex = (columnIndex == 0 ? 0 : columnIndex - 1);
            String bracketedChord = chordLocation.getChord();
            Integer bracketChordLength = bracketedChord.length();

            String debracketedChord = debracketAndWrapHtml(bracketedChord);

            chordLocaleBldr.append(addSpaces(previousColumnIndex, columnIndex)).append(debracketedChord);
            //the new startColumnIndex as been reset to the current columnIndex
            previousColumnIndex = columnIndex + bracketChordLength + 1;
        }
        
        songLine = directiveFactory.build(songLine);
        songLine = songLine.replaceAll(CHORD_PRO_CHORD_REGEX_PATTERN, ""); 
        
        return new HtmlSongLine(chordLocaleBldr.toString(), songLine);
    }  

    private String debracketAndWrapHtml(String chord) {
        String chordStyle = prop.getProperty("chord");
        Set<HtmlAttribute> attributes = new HashSet<>(); 
        
        if(null !=chordStyle){
            attributes.add(new HtmlAttribute("style", transform(chordStyle)));
        }
        
        return HtmlWrapper.wrap(
                HtmlWrapper.span,
                chord.replace(OPEN_SQUARE_BRACKET, EMPTY_SPACE).replace(CLOSE_SQUARE_BRACKET, EMPTY_SPACE), 
                attributes);
    }

    private String addSpaces(Integer previousColumnIndex, Integer startPositionColumn) {
        String spaces = EMPTY_SPACE;
        if (startPositionColumn == 0) {
            return spaces;
        } else {
            startPositionColumn = startPositionColumn - previousColumnIndex;
            for (int i = 0; i < startPositionColumn; i++) {
                spaces = spaces + SINGLE_SPACE;
            }
        }
        return spaces;
    }
    
    private Set<HtmlValueKeyPair> transform(String property){
        String[] keyValues = property.split(";");
        Set<HtmlValueKeyPair> hvkps = new HashSet<>();
        for(String keyValue : keyValues){ 
            hvkps.add(new HtmlValueKeyPair(keyValue.split(":")[0], keyValue.split(":")[1]));
        }
        return hvkps;
    }
}
