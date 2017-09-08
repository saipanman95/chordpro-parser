 package com.mdrsolutions.chordpro.parser.models;

import java.util.List;

/**
 *
 * @author mrodgers
 */
public class RawSongLine {

    public RawSongLine(String song, List<ChordLocation> chordLocations, List<DirectiveLocation> directiveLocations) {
        this.line = song;
        this.chordLocations = chordLocations;
        this.directiveLocations = directiveLocations;
    }
    
    private final String line;
    private final List<ChordLocation> chordLocations;
    private final List<DirectiveLocation> directiveLocations;

    public String getSong() {
        return line;
    } 

    public List<ChordLocation> getChordLocations() {
        return chordLocations;
    }

    public List<DirectiveLocation> getDirectiveLocations() {
        return directiveLocations;
    }  

    @Override
    public String toString() {
        return "RawSongLine{" + "line=" + line + ", chordLocations=" + chordLocations + ", directiveLocations=" + directiveLocations + '}';
    }
    
    
}
