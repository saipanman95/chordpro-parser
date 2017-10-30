package com.mdrsolutions.chordpro.parser.enums;

import java.util.Set;

/**
 * Class is used to hold attribute key value pairs. For example:
 * href='www.someurl.com'; style='font-size:12pt;color:blue;';
 *
 * @author mrodgers
 */
public class HtmlAttribute {

    private final String name;
    private final Set<HtmlValueKeyPair> attrKeyValue;

    public HtmlAttribute(String name, Set<HtmlValueKeyPair> value) {
        this.name = name;
        this.attrKeyValue = value;
    }

    public String getName() {
        return name;
    }

    /**
     * This is the value to the name attribute...but this can also contain a
     * collection of ";" delimited key value pairs separated by ":"
     *
     * @return
     */
    public String getKeyValuePair() {
        StringBuilder sb = new StringBuilder();
        for (HtmlValueKeyPair keyPair : attrKeyValue) {
            sb.append(keyPair.getKeyPair());
        }
        return sb.toString();
    }
}
