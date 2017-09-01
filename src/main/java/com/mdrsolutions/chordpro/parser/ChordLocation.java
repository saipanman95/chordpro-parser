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
public class ChordLocation {

    public ChordLocation(String chord, Integer columnIndex) {
        this.chord = chord;
        this.columnIndex = columnIndex;
    }
    
    private final String chord;
    private final Integer columnIndex;

    public String getChord() {
        return chord;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return "ChordLocation{" + "chord=" + chord + ", columnIndex=" + columnIndex + '}';
    }
    
    
}
