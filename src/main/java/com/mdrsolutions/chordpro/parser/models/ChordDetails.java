package com.mdrsolutions.chordpro.parser.models;

/**
 * Class wraps the ChordLocation object and adds a DisplayWrapper property for
 * how the object should be displayed on a page.
 *
 * @author michaelrodgers
 * @param <ChordLocation>
 */
public class ChordDetails<ChordLocation> implements Details<ChordLocation> {

    private final DisplayWrapper displayWrapper;
    private final ChordLocation location;

    public ChordDetails(DisplayWrapper displayWrapper, ChordLocation location) {
        this.displayWrapper = displayWrapper;
        this.location = location;
    }

    @Override
    public DisplayWrapper getDisplayWrapper() {
        return displayWrapper;
    }

    @Override
    public ChordLocation getLocation() {
        return location;
    }

}
