package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.factories.loaders.Loader;
import com.mdrsolutions.chordpro.parser.factories.loaders.SongFileLoader;
import com.mdrsolutions.chordpro.parser.factories.loaders.SongTextLoader;
import com.mdrsolutions.chordpro.parser.factories.processor.Processor;
import com.mdrsolutions.chordpro.parser.factories.processor.SongProcessor;
import com.mdrsolutions.chordpro.parser.factories.producers.ChordLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.producers.DirectiveLocationProducer;
import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import com.mdrsolutions.chordpro.parser.factories.producers.SimpleTextLineProducer;
import com.mdrsolutions.chordpro.parser.factories.producers.SimpleTextMetaTagDirectiveBuilder;
import com.mdrsolutions.chordpro.parser.factories.producers.SongLineProducer;
import com.mdrsolutions.chordpro.parser.factories.viewer.SystemOutPresenter;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

/**
 * Class encapsulate logic needed to parse and produce Song text from raw
 * ChordPro file format.
 *
 * @author mrodgers
 */
public class SimpleTextSongParser {

    public String parse(String filePath) {
        Producer rawSongLineProducer = new SongLineProducer(new ChordLocationProducer(), new DirectiveLocationProducer());

        Loader<List<RawSongLine>> loader = new SongFileLoader(filePath, rawSongLineProducer);

        Producer<SimpleTextSongLine, RawSongLine> simpleTextLineProducer = new SimpleTextLineProducer(new SimpleTextMetaTagDirectiveBuilder());

        Processor<List<RawSongLine>, SimpleTextSongLine, RawSongLine> songProcessor = new SongProcessor();

        Collection<SimpleTextSongLine> simpleTextSongLines = songProcessor.process(loader, simpleTextLineProducer);

        return new SystemOutPresenter<String>(simpleTextSongLines).present();
    }
    
    public String parse(String song, boolean isSongText){
        Producer rawSongLineProducer = new SongLineProducer(new ChordLocationProducer(), new DirectiveLocationProducer());

        Loader<List<RawSongLine>> loader = new SongTextLoader(song, rawSongLineProducer);

        Producer<SimpleTextSongLine, RawSongLine> simpleTextLineProducer = new SimpleTextLineProducer(new SimpleTextMetaTagDirectiveBuilder());

        Processor<List<RawSongLine>, SimpleTextSongLine, RawSongLine> songProcessor = new SongProcessor();

        Collection<SimpleTextSongLine> simpleTextSongLines = songProcessor.process(loader, simpleTextLineProducer);

        return new SystemOutPresenter<String>(simpleTextSongLines).present();
    }
    
    public String parseBase64(String song){
        byte[] decoded = Base64.getDecoder().decode(song);
        String parse = parse(new String(decoded), true);
        return Base64.getEncoder().encodeToString(parse.getBytes());
    }
}
