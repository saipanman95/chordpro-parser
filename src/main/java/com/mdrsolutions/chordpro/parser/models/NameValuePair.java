package com.mdrsolutions.chordpro.parser.models;

/**
 * Simple NameValuePair class that can receive dynamic types for name and value
 * @author michaelrodgers
 * @param <NM> name
 * @param <VL> value
 */
public class NameValuePair<NM, VL> {
    private final NM name;
    private final VL value;

    public NameValuePair(NM name, VL value) {
        this.name = name;
        this.value = value;
    }

    public NM getName() {
        return name;
    }

    public VL getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NameValuePair{" + "name=" + name + ", value=" + value + '}';
    }
    
}
