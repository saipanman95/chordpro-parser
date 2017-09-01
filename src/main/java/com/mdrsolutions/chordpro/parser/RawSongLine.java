/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.chordpro.parser;

import java.util.List;

/**
 *
 * @author mrodgers
 */
public class RawSongLine {

    public RawSongLine(String song, List<ChordLocation> chordLocations) {
        this.line = song;
        this.chordLocations = chordLocations;
    }
    
    private final String line;
    private final List<ChordLocation> chordLocations;

    public String getSong() {
        return line;
    }

    public List<ChordLocation> getChordLocation() {
        return chordLocations;
    }

    @Override
    public String toString() {
        return "SongLine{" + "song=" + line + ", chordLocations=" + chordLocations + '}';
    }
    
    
}
