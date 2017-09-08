package com.mdrsolutions.chordpro.parser.models;

import com.mdrsolutions.chordpro.parser.enums.Directive;
import com.mdrsolutions.chordpro.parser.enums.SectionDirectiveEnum;
import com.mdrsolutions.chordpro.parser.enums.MetaTagsDirectiveEnum;

/**
 * This class identifies the directive used and the columnIndex of that
 * information.
 *
 * @author mrodgers
 */
public class DirectiveLocation implements Location<Directive, Integer> {

    public DirectiveLocation(String directive, Integer columnIndex) {
        this.directive = directive;
        this.columnIndex = columnIndex;
    }

    private final String directive;
    private final Integer columnIndex;

    public Directive getDirective() {
        return determineDirective(this.directive);
    }

    @Override
    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return "DirectiveLocation{" + "directive=" + directive + ", columnIndex=" + columnIndex + '}';
    }

    private Directive determineDirective(String value) {

        Directive d = MetaTagsDirectiveEnum.none;

        for (MetaTagsDirectiveEnum metaDirective : MetaTagsDirectiveEnum.values()) {
            if (metaDirective.getNorm().equalsIgnoreCase(value) || metaDirective.getAbbr().equalsIgnoreCase(value)) {
                d = metaDirective;
            }
        }

        for (SectionDirectiveEnum sectionDirective : SectionDirectiveEnum.values()) {
            if (sectionDirective.getNorm().equalsIgnoreCase(value) || sectionDirective.getAbbr().equalsIgnoreCase(value)) {
                d = sectionDirective;
            }
        }
        return d;
    }

    @Override
    public NameValuePair<Directive, Integer> information() {
        return new NameValuePair<>(getDirective(), getColumnIndex());
    }

}
