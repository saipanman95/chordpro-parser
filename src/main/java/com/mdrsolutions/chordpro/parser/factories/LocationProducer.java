package com.mdrsolutions.chordpro.parser.factories;

import com.mdrsolutions.chordpro.parser.models.Location;
import java.util.List;

/**
 * This is an abstract class that produces a Location Object; A location object
 * contains information about where items can be found on a line by a
 * columnIndex.
 *
 * @author michaelrodgers
 * @param <LO> Location Object
 */
public interface LocationProducer<LO extends Location> {

    List<Location> produce(String songLine);
}
