package com.mdrsolutions.chordpro.parser.models;

/**
 * Location class identifies the columnInded for the directive or chord and a
 * NameValuePair object containing the Directive/Chord value and again the
 * columnIndex.
 *
 * @author michaelrodgers
 * @param <NM>
 * @param <VL>
 */
public interface Location<NM, VL> {

    Integer getColumnIndex();

    NameValuePair<NM, VL> information();
}
