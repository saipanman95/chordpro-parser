package com.mdrsolutions.chordpro.parser.factories.processor;

import com.mdrsolutions.chordpro.parser.factories.loaders.Loader;
import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SongProcessor implements Processor {

    @Override
    public Collection process(Loader songLoader, Producer producer) {

        List finalSongLines = new LinkedList<>();

        Collection songs = songLoader.load();

        songs.forEach((song) -> {
            finalSongLines.add(producer.produce(song));
        });

        return finalSongLines;
    }

}
