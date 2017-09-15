package com.mdrsolutions.chordpro.parser.models;

/**
 * Class is used for displaying each line with either chord or song line
 * containing formatted directives
 *
 * @author mrodgers
 */
public class SimpleTextSongLine {

    public SimpleTextSongLine(String chordLine, String songLine) {
        this.chordLine = chordLine;
        this.songLine = songLine;
    }

    private final String chordLine;
    private final String songLine;

    public String getChordLine() {
        return chordLine;
    }

    public String getSongLine() {
        return songLine;
    }

    @Override
    public String toString() {
        return "ArrangedSongLine{" + "chordLine=" + chordLine + ", songLine=" + songLine + '}';
    }

}
