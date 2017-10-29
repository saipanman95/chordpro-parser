package com.mdrsolutions.chordpro.parser.enums;

import java.util.Map;

/**
 * Enum of all meta tags directives that follow a patter of {tag: text }. Each
 * enum contains the normative tag name and a possible abbreviation. If no
 * abbreviation is found use the normative tag name for parsing.
 *
 * @author mrodgers
 */
public enum MetaTagsDirectiveEnum implements Directive {

    title("title", "t"),
    subtitle("subtitle", "st"),
    album(),
    artist("artist", "a"),
    author(),
    key("key", "k"),
    capo(),
    ipodid("ipodid", "ip"),
    comment("comment", "c"),
    guitarComment("guitar_comment", "gc"),
    commentBold("comment_bold", "cb"),
    commentItalic("comment_italic", "ci"),
    tempo(),
    time(),
    duration(),
    book(),
    number(),
    flow(),
    midi(),
    midiindex(),
    pitch(),
    keywords(),
    topic(),
    copyright(),
    footer(),
    ccli(),
    restrictions(),
    none();

    MetaTagsDirectiveEnum() {
        this.norm = name();
        this.abbr = name();
    }

    MetaTagsDirectiveEnum(String norm, String abbr) {
        this.norm = norm;
        this.abbr = abbr;
    }

    private final String norm;
    private final String abbr;

    @Override
    public String getAbbr() {
        return abbr;
    }

    @Override
    public String getNorm() {
        return norm;
    }

    @Override
    public DirectiveTypeEnum getType() {
        return DirectiveTypeEnum.META_TAG;
    }

    @Override
    public Map<String, String> directiveWrapper(String start, String end) {
        return wrapper(start, end);
    }

}
