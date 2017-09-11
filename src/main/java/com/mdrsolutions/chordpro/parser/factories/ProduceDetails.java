package com.mdrsolutions.chordpro.parser.factories;

import com.mdrsolutions.chordpro.parser.models.Details;
import java.util.List;

/**
 * This is an abstract class that produces a Details Object; A Details object
 * contains information about how Location Objects are styled and wrapped in
 * HTML DOM Objects;
 *
 * @author michaelrodgers
 * @param <DTL> Details Object
 */
public interface ProduceDetails<DTL extends Details> {

    List<DTL> describe(String songLine);
}
