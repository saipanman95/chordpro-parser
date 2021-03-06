package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.List;

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

    public SimpleTextLineProducer(final SimpleTextMetaTagDirectiveBuilder directiveFactory) {
        this.directiveFactory = directiveFactory;
    }

    private final SimpleTextMetaTagDirectiveBuilder directiveFactory;
    
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
        songLine = directiveFactory.build(songLine);
        
        songLine = songLine.replaceAll(CHORD_PRO_CHORD_REGEX_PATTERN, "");

        return new SimpleTextSongLine(chordLocaleBldr.toString(), songLine);
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
