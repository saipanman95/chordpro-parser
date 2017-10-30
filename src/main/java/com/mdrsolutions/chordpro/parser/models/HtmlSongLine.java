package com.mdrsolutions.chordpro.parser.models;

import com.mdrsolutions.chordpro.parser.enums.HtmlWrapper;

/**
 * Class is used for displaying each line with either chord or song line
 * containing formatted directives
 *
 * @author mrodgers
 */
public class HtmlSongLine {

    public HtmlSongLine(String chordLine, String songLine) {
        this.chordLine = HtmlWrapper.wrap(HtmlWrapper.div, chordLine, null);
        this.songLine = HtmlWrapper.wrap(HtmlWrapper.div, songLine, null);
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
