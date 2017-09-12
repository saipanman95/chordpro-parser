package com.mdrsolutions.chordpro.parser.factories;

import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.DirectiveLocation;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.List;

/**
 *
 * @author mrodgers
 */
public class SongLineProducer {
    
    private final ChordLocationProducer chordLocations;
    private final DirectiveLocationProducer directiveLocations;

    public SongLineProducer(ChordLocationProducer chordLocations, DirectiveLocationProducer directiveLocations) {
        this.chordLocations = chordLocations;
        this.directiveLocations = directiveLocations;
    }
    
    public RawSongLine produce(String songLine){
        
        List<ChordLocation> chordDetail = chordLocations.produce(songLine);   
        List<DirectiveLocation> directiveDetail = directiveLocations.produce(songLine);
        
        return new RawSongLine(songLine, chordDetail, directiveDetail);
    }
}
