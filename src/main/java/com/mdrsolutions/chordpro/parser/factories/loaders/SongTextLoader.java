package com.mdrsolutions.chordpro.parser.factories.loaders;

import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Loads a complete song text that uses standard unix or windows line
 * breaks;
 *
 * @author mrodgers
 */
public class SongTextLoader implements Loader {

    private final String completeSong;
    private final Producer lineProducer;

    public SongTextLoader(String completeSongText, Producer lineProducer) {
        this.completeSong = completeSongText;
        this.lineProducer = lineProducer;
    }

    @Override
    public Collection load() {
        List rawSongLines = new LinkedList<>();

        readFile(completeSong).forEach((songLine) -> {
            rawSongLines.add(lineProducer.produce(songLine));
        });
        return rawSongLines;
    }

    private List<String> readFile(String completeSong) {
        List<String> songLines = new LinkedList();
        for (String line : completeSong.split("\\r?\\n")) {
            songLines.add(line);
        }

        return songLines;
    }

}
