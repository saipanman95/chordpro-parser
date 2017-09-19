package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.factories.producers.ChordLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.producers.DirectiveLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.loaders.Loader;
import com.mdrsolutions.chordpro.parser.factories.producers.SimpleTextLineProducer;
import com.mdrsolutions.chordpro.parser.factories.loaders.SongFileLoader;
import com.mdrsolutions.chordpro.parser.factories.processor.Processor;
import com.mdrsolutions.chordpro.parser.factories.processor.SongProcessor;
import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import com.mdrsolutions.chordpro.parser.factories.producers.SongLineProducer;
import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mrodgers
 */
public class Main {

    private static final String PATH = "/Users/mrodgers/songfiles/testsong.chordpro";
    private static final String SONG
            = "{c: Verse 1: }\n"
            + "{soi}(das){eoi} Bless the[C] Lord, O my[G] {soh}(now dance){eoh}soul,[D/F#] O my [Em] soul,\n"
            + "[C]Worship His ho[G]ly n[Dsus4]ame.    [D]\n"
            + "Sing like [C]never be[Em]fore, [C]   [D]O m[Em]y soul.\n"
            + "I'll[C] worship Your ho[D]ly na[C/G]me.   [G]";

    public static void main(String[] args) {

        Producer rawSongLineProducer = new SongLineProducer(new ChordLocationProducer(), new DirectiveLocationProducer());

        Loader<List<RawSongLine>> loader = new SongFileLoader(PATH, rawSongLineProducer);

        Producer<SimpleTextSongLine, RawSongLine> simpleTextLineProducer = new SimpleTextLineProducer();

        Processor<List<RawSongLine>, SimpleTextSongLine, RawSongLine> songProcessor = new SongProcessor();

        Collection<SimpleTextSongLine> song = songProcessor.process(loader, simpleTextLineProducer);

        for(SimpleTextSongLine tsl : song){
            System.out.println(tsl);
        }
    }

}
