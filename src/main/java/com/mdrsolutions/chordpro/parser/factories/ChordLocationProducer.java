package com.mdrsolutions.chordpro.parser.factories;

import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.Location;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChordLocationProducer<LO extends ChordLocation> implements LocationProducer<LO> {

    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]";
    
    @Override
    public List<Location> produce(String songLine) {
        Pattern pattern = Pattern.compile(CHORD_PRO_CHORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine); 
        List<Location> chords = new LinkedList<>();

        int chordLocation = 0;
        while (matcher.find()) {
            String rawChord = matcher.group(0);
            chordLocation = songLine.indexOf(rawChord, chordLocation);
            chords.add(new ChordLocation(rawChord, chordLocation));
            
            //adding one so it keeps looking forward on next indexOf method
            chordLocation++;
        } 
        return chords;
    }
    
}
