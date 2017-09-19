package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.models.ChordLocation;
import com.mdrsolutions.chordpro.parser.models.DirectiveLocation;
import com.mdrsolutions.chordpro.parser.models.RawSongLine;
import java.util.List;

/**
 *
 * @author mrodgers
 */
public class SongLineProducer implements Producer<RawSongLine, String> {
    
    private final LocationProducer chordProducerLocations;
    private final LocationProducer directiveProducerLocations;

    /**
     * 
     * @param chordProducerLocations
     * @param directiveProducerLocations 
     */
    public SongLineProducer(LocationProducer chordProducerLocations, LocationProducer directiveProducerLocations) {
        this.chordProducerLocations = chordProducerLocations;
        this.directiveProducerLocations = directiveProducerLocations;
    }
    
    @Override
    public RawSongLine produce(String songLine){
        
        List<ChordLocation> chordDetail = chordProducerLocations.produce(songLine);   
        List<DirectiveLocation> directiveDetail = directiveProducerLocations.produce(songLine);
        
        return new RawSongLine(songLine, chordDetail, directiveDetail);
    }
 
}
