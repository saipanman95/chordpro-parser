package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.enums.Directive;
import com.mdrsolutions.chordpro.parser.enums.MetaTagsDirectiveEnum;
import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mrodgers
 */
public class SimpleTextLineProducer implements Producer<SimpleTextSongLine, RawSongLine> {

    private static final String EMPTY_SPACE = "";
    private static final String SINGLE_SPACE = " ";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";
    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]";
    private static final String META_TAGS_DIRECT_REGEX_PATTERN = "\\{.:(.*?)\\}";
    private static final String SECTIONAL_DIRECT_REGEX_PATTERN = "\\{(.*?)\\}.*?\\{(.*?)\\}";

    @Override
    public SimpleTextSongLine produce(RawSongLine rawSongLine) {

        List<ChordLocation> chordLocales = rawSongLine.getChordLocations();
        String songLine = rawSongLine.getSong();

        StringBuilder chordLocaleBldr = new StringBuilder();
        Integer previousColumnIndex = 0;

        for (ChordLocation chordLocation : chordLocales) {

            Integer columnIndex = chordLocation.getColumnIndex();
            columnIndex = (columnIndex == 0 ? 0 : columnIndex - 1);
            String bracketedChord = chordLocation.getChord();
            Integer bracketChordLength = bracketedChord.length();

            String debracketedChord = debracket(bracketedChord);

            chordLocaleBldr.append(addSpaces(previousColumnIndex, columnIndex)).append(debracketedChord);
            //the new startColumnIndex as been reset to the current columnIndex
            previousColumnIndex = columnIndex + bracketChordLength + 1;
        }
        songLine = simpleTextDirectiveHandler(songLine);
        songLine = songLine.replaceAll(CHORD_PRO_CHORD_REGEX_PATTERN, "");

        return new SimpleTextSongLine(chordLocaleBldr.toString(), songLine);
    }

    private String simpleTextDirectiveHandler(String songLine) {
        Directive d = MetaTagsDirectiveEnum.comment;
        Map<String,String> directivesMap = d.directiveWrapper("{", ":");
        String commentAbbr = directivesMap.get(Directive.ABBR);
        String commentNorm = directivesMap.get(Directive.NORM);

        String sl = songLine;

        Pattern pattern = Pattern.compile(META_TAGS_DIRECT_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);
        
        while (matcher.find()) {

                String original = matcher.group(0); 
                String without = matcher.group(1);  

            if (songLine.contains(commentAbbr)) {
                sl = sl.replace(original, without.toUpperCase().trim());

            } else if (songLine.contains(commentNorm)) {
                sl = sl.replace(original, without.toUpperCase().trim());
            } 
        }
        
        //removing all sectional directive tags content for simple text display
        sl = eliminateRemainingDirectives(sl);
        return sl;
    }

    private String eliminateRemainingDirectives(String songLine){
        Pattern pattern = Pattern.compile(SECTIONAL_DIRECT_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);
        
        while(matcher.find()){
            String sectWithoutStart = matcher.group(1); 
            String sectWithoutEnd = matcher.group(2); 
            songLine = songLine.replace("{"+sectWithoutStart+"}","").replace("{"+sectWithoutEnd+"}", "");
        }
        
        return songLine;
    }
    
    private String debracket(String chord) {
        return chord.replace(OPEN_SQUARE_BRACKET, EMPTY_SPACE).replace(CLOSE_SQUARE_BRACKET, EMPTY_SPACE);
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
}
