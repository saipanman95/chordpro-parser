/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.chordpro.parser.factories.processor;

import com.mdrsolutions.chordpro.parser.factories.loaders.Loader;
import com.mdrsolutions.chordpro.parser.factories.producers.Producer;
import java.util.Collection;

/**
 *
 * @author mrodgers
 * @param <SC>
 * @param <MSL>
 * @param <SL>
 */
public interface Processor<SC,MSL,SL> {
    
    Collection<MSL> process(Loader<SC> loader, Producer<MSL,SL> processor);
}
