package com.mdrsolutions.chordpro.parser.factories.loaders;

import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SongFileLoader implements Loader {

    private final String songPath;
    private final Producer lineProducer;
    
    public SongFileLoader(String songPath, Producer lineProducer) {
        this.songPath = songPath;
        this.lineProducer = lineProducer;
    }

    @Override
    public Collection load() {
        List rawSongLines = new LinkedList<>();

        readFile(songPath).forEach((songLine) -> {
            rawSongLines.add(lineProducer.produce(songLine));
        });
        return rawSongLines;
    }

    private List<String> readFile(String songPath) {
        List<String> songLines = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader(songPath))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                songLines.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return songLines;
    }

}
