package com.mdrsolutions.chordpro.parser.models;

import com.mdrsolutions.chordpro.parser.enums.HtmlWrapper;
import java.util.List; 

/**
 *
 * @author michaelrodgers
 */
public class DisplayWrapper {
    private final NameValuePair<HtmlWrapper,String> htmlWrapper;
    private final String styleClass;
    private final List<NameValuePair<String,String>> attributes;

    public DisplayWrapper(NameValuePair<HtmlWrapper, String> htmlWrapper, String styleClass, List<NameValuePair<String, String>> attributes) {
        this.htmlWrapper = htmlWrapper;
        this.styleClass = styleClass;
        this.attributes = attributes;
    }

    public NameValuePair<HtmlWrapper, String> getHtmlWrapper() {
        return htmlWrapper;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public List<NameValuePair<String, String>> getAttributes() {
        return attributes;
    }
    
    public void addAttribute(NameValuePair<String,String> attribute){
        getAttributes().add(attribute);
    } 
    
}
