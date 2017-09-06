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
public enum SectionDirectiveEnum implements Directive{
    startOfBridge("start_of_bridge", "sob"),
    endOfBridge("end_of_bridge", "eob"),
    startOfChours("start_of_chorus", "soc"),
    endOfChorus("end_of_chorus", "eoc"),
    startOfTab("start_of_tab", "sot"),
    endOfTab("end_of_tab", "eot"),
    newPage("new_page", "np"),
    newPhysicalPage("new_physical_page", "npp");

    SectionDirectiveEnum(String norm, String abbr) {
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
        return DirectiveTypeEnum.SECTION;
    }

}
