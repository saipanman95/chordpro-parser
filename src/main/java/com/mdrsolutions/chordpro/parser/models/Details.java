package com.mdrsolutions.chordpro.parser.models;

/**
 * Class wraps the Location object, which could be a directive location object
 * or chord location object and adds a DisplayWrapper property for how the
 * object should be displayed on a page.
 *
 * @author michaelrodgers
 * @param <LO>
 */
public interface Details<LO> {

    DisplayWrapper getDisplayWrapper();

    LO getLocation();
}
