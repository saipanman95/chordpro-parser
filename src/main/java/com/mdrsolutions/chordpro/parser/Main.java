package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.factories.ChordLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.DirectiveLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.SongLineProducer;
import com.mdrsolutions.chordpro.parser.models.DirectiveLocation;
import com.mdrsolutions.chordpro.parser.models.ArrangedSongLine;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mrodgers
 */
public class Main {

    private static final String EMPTY_SPACE = "";
    private static final String SINGLE_SPACE = " ";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";
    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]";
    private static final String CHORD_PRO_DIRECTIVE_REGEX_PATTERN = "\\{(.*?)\\}";
    private static final String SONG
            = "{c: Verse 1: }Bless the[C] Lord, O my[G] soul,[D/F#] O my [Em] soul,\n"
            + "[C]Worship His ho[G]ly n[Dsus4]ame.    [D]\n"
            + "Sing like [C]never be[Em]fore, [C]   [D]O m[Em]y soul.\n"
            + "I'll[C] worship Your ho[D]ly na[C/G]me.   [G]";
   static int num= 0;
   
    public static void main(String[] args) {
        
        SongLineProducer lineProducer = new SongLineProducer(new ChordLocationProducer(), new DirectiveLocationProducer());
        System.out.println("num = " + num);
        System.out.println(SONG);
        List<RawSongLine> songLines = new LinkedList<>();
        List<ArrangedSongLine> arrangedSongLines = new LinkedList<>();
        
        //identify song line chord components
        songLines(SONG).forEach((songLine) -> {
            songLines.add(lineProducer.produce(songLine));
        });

        //transform Song lines
        songLines.forEach((line) -> {
            arrangedSongLines.add(transformSongLine(line));
        });
        
        //print Arranged Chords and Song Lines
        arrangedSongLines.forEach((line) -> {
            System.out.println(line);
        });
        num = num +1;
        System.out.println("num = " + num);
    }

    private static List<String> songLines(String song) {
        List<String> songLines = new LinkedList<>();
        String[] split = song.split("\\n");
        songLines.addAll(Arrays.asList(split));
        return songLines;
    } 

    private static ArrangedSongLine transformSongLine(RawSongLine rawSongLine) {
        List<ChordLocation> chordLocations = rawSongLine.getChordLocations();
        String songLine = rawSongLine.getSong(); 
        
        StringBuilder chordLocaleBldr = new StringBuilder();
        Integer previousColumnIndex = 0;
        
        for(ChordLocation chordLocation : chordLocations){
            
            Integer columnIndex = chordLocation.getColumnIndex();
            columnIndex = (columnIndex == 0 ? 0 : columnIndex -1);
            String bracketChord = chordLocation.getChord();
            Integer bracketChordLength = bracketChord.length();
            
            String debracketedChord = debracket(bracketChord);
            
            chordLocaleBldr.append(addSpaces(previousColumnIndex, columnIndex)).append(debracketedChord);            
            //the new startColumnIndex as been reset to the current columnIndex
            previousColumnIndex = columnIndex + bracketChordLength +1;
        }
        
        songLine = songLine.replaceAll(CHORD_PRO_CHORD_REGEX_PATTERN, "");
        System.out.println(chordLocaleBldr.toString());
        System.out.println(songLine);
        
        return new ArrangedSongLine(chordLocaleBldr.toString(), songLine);
    }
    
    private static String debracket(String chord){
        return chord.replace(OPEN_SQUARE_BRACKET, EMPTY_SPACE).replace(CLOSE_SQUARE_BRACKET, EMPTY_SPACE);
    }
    
    private static String addSpaces(Integer previousColumnIndex, Integer startPositionColumn){
        String spaces = EMPTY_SPACE;
        if(startPositionColumn == 0){
            return spaces;
        } else {
            startPositionColumn = startPositionColumn - previousColumnIndex;
            for(int i = 0; i < startPositionColumn; i++){
                spaces = spaces + SINGLE_SPACE;
            }
        }
        return spaces;
    }
}
