package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.enums.Directive;
import com.mdrsolutions.chordpro.parser.enums.SectionDirectiveEnum;
import com.mdrsolutions.chordpro.parser.enums.MetaTagsDirectiveEnum;

/**
 *
 * @author mrodgers
 */
public class DirectiveLocation {

    public DirectiveLocation(String directive, Integer columnIndex) {
        this.directive = directive;
        this.columnIndex = columnIndex;
    }
    
    private final String directive;
    private final Integer columnIndex;

    public Directive getDirective() {
        return determineDiretive(this.directive);
    }
 

    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return "DirectiveLocation{" + "directive=" + directive + ", columnIndex=" + columnIndex + '}';
    }
    
    private Directive determineDiretive(String value){ 
        
        Directive d = MetaTagsDirectiveEnum.none;
                
        for(MetaTagsDirectiveEnum metaDirective : MetaTagsDirectiveEnum.values()){
            if(metaDirective.getNorm().equalsIgnoreCase(value) || metaDirective.getAbbr().equalsIgnoreCase(value)){
                d = metaDirective;
            }
        }
        
        for(SectionDirectiveEnum sectionDirective : SectionDirectiveEnum.values()){
            if(sectionDirective.getNorm().equalsIgnoreCase(value) || sectionDirective.getAbbr().equalsIgnoreCase(value)){
                d = sectionDirective;
            }
        }
        return d;
    }
    
}
