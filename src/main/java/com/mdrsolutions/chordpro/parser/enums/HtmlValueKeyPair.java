package com.mdrsolutions.chordpro.parser.enums;

/**
 * This is the child attribute of a HtmlAttribute. Almost exclusively used with
 * style attributes but is designed to be used in other instances if needed.
 *
 * @author mrodgers
 */
public class HtmlValueKeyPair {

    public HtmlValueKeyPair(String key, String value) {
        this.key = key;
        this.value = value;
    }
    private final String key;
    private final String value;

    public String getKeyPair() {
        return key + ":" + value + ";";
    }

}
