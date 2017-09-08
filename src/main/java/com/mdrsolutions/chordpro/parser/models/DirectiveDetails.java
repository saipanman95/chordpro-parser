package com.mdrsolutions.chordpro.parser.models;

/**
 * Class wraps the DirectiveLocation object and adds a DisplayWrapper property
 * for how the object should be displayed on a page.
 *
 * @author michaelrodgers
 * @param <DirectiveLocation>
 */
public class DirectiveDetails<DirectiveLocation> implements Details<DirectiveLocation> {

    private final DisplayWrapper displayWrapper;
    private final DirectiveLocation location;

    public DirectiveDetails(DisplayWrapper displayWrapper, DirectiveLocation location) {
        this.displayWrapper = displayWrapper;
        this.location = location;
    }

    @Override
    public DisplayWrapper getDisplayWrapper() {
        return displayWrapper;
    }

    @Override
    public DirectiveLocation getLocation() {
        return location;
    }

}
