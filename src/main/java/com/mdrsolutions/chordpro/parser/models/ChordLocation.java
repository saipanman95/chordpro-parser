package com.mdrsolutions.chordpro.parser.models;

/**
 * This class identifies the Chord used and the columnIndex of that chord.
 *
 * @author mrodgers
 */
public class ChordLocation implements Location<String, Integer> {

    public ChordLocation(String chord, Integer columnIndex) {
        this.chord = chord;
        this.columnIndex = columnIndex;
    }

    private final String chord;
    private final Integer columnIndex;

    public String getChord() {
        return chord;
    }

    @Override
    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return "ChordLocation{" + "chord=" + chord + ", columnIndex=" + columnIndex + '}';
    }

    @Override
    public NameValuePair<String, Integer> information() {
        return new NameValuePair<>(getChord(), getColumnIndex());
    }

}
