package com.mdrsolutions.chordpro.parser.factories;

import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SongFileLoader implements Loader<List<RawSongLine>, String, SongLineProducer> {

    private final SongLineProducer lineProducer;

    public SongFileLoader(SongLineProducer lineProducer) {
        this.lineProducer = lineProducer;
    }

    @Override
    public List<RawSongLine> load(String songPath) {
        List<RawSongLine> rawSongLines = new LinkedList<>();

        for (String songLine : readFile(songPath)) {
            rawSongLines.add(lineProducer.produce(songLine));
        }
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
