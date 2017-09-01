/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.chordpro.parser;

/**
 *
 * @author mrodgers
 */
public class ArrangedSongLine {

    public ArrangedSongLine(String chordLine, String songLine) {
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
