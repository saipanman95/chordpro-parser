package com.mdrsolutions.chordpro.parser.enums;

import java.util.Map;

/**
 *
 * @author mrodgers
 */
public enum SectionDirectiveEnum implements Directive{
    startOfBridge("start_of_bridge", "sob", HtmlWrapper.div),
    endOfBridge("end_of_bridge", "eob", HtmlWrapper.div),
    startOfChours("start_of_chorus", "soc", HtmlWrapper.div),
    endOfChorus("end_of_chorus", "eoc", HtmlWrapper.div),
    startOfTab("start_of_tab", "sot", HtmlWrapper.div),
    endOfTab("end_of_tab", "eot", HtmlWrapper.div),
    newPage("new_page", "np", HtmlWrapper.div),
    newPhysicalPage("new_physical_page", "npp", HtmlWrapper.div);

    SectionDirectiveEnum(String norm, String abbr, HtmlWrapper htmlWrapper) {
        this.norm = norm;
        this.abbr = abbr;
        this.htmlWrapper = htmlWrapper;
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
    public DirectiveTypeEnum getType(){
        return DirectiveTypeEnum.SECTION;
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
