package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.Location;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mrodgers
 * @param <LO> 
 */
public class ChordLocationProducer<LO extends ChordLocation> implements LocationProducer<LO> {

    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]";
    private static final String META_TAGS_DIRECT_REGEX_PATTERN = "\\{.:(.*?)\\}";
    private static final String SECTIONAL_DIRECT_REGEX_PATTERN = "\\{(.*?)\\}.*?\\{(.*?)\\}";
    
    @Override
    public List<Location> produce(String songLine) {
        Pattern pattern = Pattern.compile(CHORD_PRO_CHORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine); 
        List<Location> chords = new LinkedList<>();
        
        songLine = eliminateDirectiveWrappers(songLine);
        
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
    
    private String eliminateDirectiveWrappers(String songLine){
        Matcher metaTagDirectMatcher = Pattern.compile(META_TAGS_DIRECT_REGEX_PATTERN).matcher(songLine); 
        Matcher sectionalDirectMatcher = Pattern.compile(SECTIONAL_DIRECT_REGEX_PATTERN).matcher(songLine); 
        
        while(metaTagDirectMatcher.find()){
            String metaOriginal = metaTagDirectMatcher.group(0);
            String metaWithout = metaTagDirectMatcher.group(1); 
            songLine = songLine.replace(metaOriginal, metaWithout);
        }
        
        while(sectionalDirectMatcher.find()){
            String sectWithoutStart = sectionalDirectMatcher.group(1); 
            String sectWithoutEnd = sectionalDirectMatcher.group(2); 
            songLine = songLine.replace("{"+sectWithoutStart+"}","").replace("{"+sectWithoutEnd+"}", "");
        }
        
        
        return songLine;
    }
    
}
