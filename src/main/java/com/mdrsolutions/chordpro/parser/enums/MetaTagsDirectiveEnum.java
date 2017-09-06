/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.chordpro.parser.enums;

/**
 *
 * @author mrodgers
 */
public enum MetaTagsDirectiveEnum implements Directive {

    title("title", "t"),
    subtitle("subtitle", "su"),
    album(),
    artist("artist", "a"),
    author(),
    key("key", "k"),
    capo(),
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
    public DirectiveTypeEnum getType(){
        return DirectiveTypeEnum.META_TAG;
    }
    

}