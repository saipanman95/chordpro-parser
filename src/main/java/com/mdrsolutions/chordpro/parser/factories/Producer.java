package com.mdrsolutions.chordpro.parser.factories;

/**
 *
 * @author mrodgers
 * @param <MSL>
 * @param <SL>
 */
public interface Producer<MSL,SL> {
    
    MSL produce(SL sl);
}
