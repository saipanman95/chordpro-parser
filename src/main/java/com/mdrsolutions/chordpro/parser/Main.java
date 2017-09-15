package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.factories.ChordLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.DirectiveLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.SimpleTextLineProducer;
import com.mdrsolutions.chordpro.parser.factories.SongLineProducer;
import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mrodgers
 */
public class Main {
 
    private static final String SONG
            = "{c: Verse 1: }Bless the[C] Lord, O my[G] soul,[D/F#] O my [Em] soul,\n"
            + "[C]Worship His ho[G]ly n[Dsus4]ame.    [D]\n"
            + "Sing like [C]never be[Em]fore, [C]   [D]O m[Em]y soul.\n"
            + "I'll[C] worship Your ho[D]ly na[C/G]me.   [G]";
   static int num= 0;
   
    public static void main(String[] args) {
        
        SongLineProducer lineProducer = new SongLineProducer(new ChordLocationProducer(), new DirectiveLocationProducer());
        SimpleTextLineProducer textLineProducer = new SimpleTextLineProducer();
        
        System.out.println("num = " + num);
        System.out.println(SONG);
        List<RawSongLine> songLines = new LinkedList<>();
        List<SimpleTextSongLine> arrangedSongLines = new LinkedList<>();
        
        //identify song line chord components
        songLines(SONG).forEach((songLine) -> {
            songLines.add(lineProducer.produce(songLine));
        });

        //transform Song lines
        songLines.forEach((line) -> {
            arrangedSongLines.add(textLineProducer.produce(line));
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
    
}
