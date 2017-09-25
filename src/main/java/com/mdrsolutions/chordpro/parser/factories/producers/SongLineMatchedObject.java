package com.mdrsolutions.chordpro.parser.factories.producers;

/**
 * Class is used for handling whether an object in songline matched or not. This
 * is a tuple object, which includes the songline and boolean as to whether a
 * match was made. If matched boolean is false, the songLine parameter should be
 * disregarded.
 *
 * @author michaelrodgers
 */
public class SongLineMatchedObject {

    public SongLineMatchedObject(final Boolean matched, final String songLine) {
        this.songLine = songLine;
        this.matched = matched;
    }

    private final Boolean matched;
    private final String songLine;

    public Boolean getMatched() {
        return matched;
    }

    public String getSongLine() {
        return songLine;
    }

    @Override
    public String toString() {
        return "SongLineMatchedObject{" + "matched=" + matched + ", songLine=" + songLine + '}';
    }
}
