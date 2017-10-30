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

    title("title", "t", HtmlWrapper.h1),
    subtitle("subtitle", "st", HtmlWrapper.h3),
    album(),
    artist("artist", "a", HtmlWrapper.h4),
    author(),
    key("key", "k", HtmlWrapper.h4),
    capo(),
    ipodid("ipodid", "ip"),
    comment("comment", "c", HtmlWrapper.div),
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
        this.htmlWrapper = HtmlWrapper.div;
    }

    MetaTagsDirectiveEnum(String norm, String abbr) {
        this.norm = norm;
        this.abbr = abbr;
        this.htmlWrapper = HtmlWrapper.div;
    }
    
    MetaTagsDirectiveEnum(String norm, String abbr, HtmlWrapper hw) {
        this.norm = norm;
        this.abbr = abbr;
        this.htmlWrapper = hw;
    }

    private final String norm;
    private final String abbr;
    private final HtmlWrapper htmlWrapper;

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
    
    @Override
    public HtmlWrapper getHtml(){
        return this.htmlWrapper;
    }

}
