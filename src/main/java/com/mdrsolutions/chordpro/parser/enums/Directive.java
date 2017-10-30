package com.mdrsolutions.chordpro.parser.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mrodgers
 */
public interface Directive {

    public static final String ABBR = "abbr";
    public static final String NORM = "norm";
    
    String getAbbr();

    String getNorm();
    
    HtmlWrapper getHtml();
    
    Map<String,String> directiveWrapper(String start, String end);
    
    default Map<String,String> wrapper(String st, String end){
        Map<String,String> wrapper = new HashMap<>();
        wrapper.put(ABBR, st+getAbbr()+end);
        wrapper.put(NORM, st+getNorm()+end);
        return wrapper;
    }

    DirectiveTypeEnum getType();
}
