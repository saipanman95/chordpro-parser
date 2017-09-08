package com.mdrsolutions.chordpro.parser;

import com.mdrsolutions.chordpro.parser.models.DirectiveLocation;
import com.mdrsolutions.chordpro.parser.enums.DirectiveTypeEnum;
import com.mdrsolutions.chordpro.parser.enums.MetaTagsDirectiveEnum;
import com.mdrsolutions.chordpro.parser.enums.Directive;
import com.mdrsolutions.chordpro.parser.enums.SectionDirectiveEnum;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrodgers
 */
public class DirectiveLocationTest {
     
    DirectiveLocation directiveLocation;
     

    /**
     * Test of getDirective method, of class DirectiveLocation.
     */
    @Test
    public void testNew_Page_Directive() {
        directiveLocation = new DirectiveLocation("new_page", Integer.SIZE);
        Directive directive = directiveLocation.getDirective();
        DirectiveTypeEnum type = directive.getType();
        assertEquals(type, DirectiveTypeEnum.SECTION); 
        assertEquals(SectionDirectiveEnum.newPage.getNorm(), directive.getNorm());
        assertEquals(SectionDirectiveEnum.newPage.getAbbr(),directive.getAbbr());
    }
    
    @Test
    public void testStart_Of_Chorus_Directive() {
        directiveLocation = new DirectiveLocation("start_of_chorus", Integer.SIZE);
        Directive directive = directiveLocation.getDirective();
        DirectiveTypeEnum type = directive.getType();
        assertEquals(type, DirectiveTypeEnum.SECTION); 
        assertEquals(SectionDirectiveEnum.startOfChours.getNorm(), directive.getNorm());
        assertEquals(SectionDirectiveEnum.startOfChours.getAbbr(),directive.getAbbr());
    }
    
    @Test
    public void testComment_Directive() { 
        
        directiveLocation = new DirectiveLocation("comment", Integer.SIZE);
        Directive directive = directiveLocation.getDirective();
        DirectiveTypeEnum type = directive.getType();
        assertEquals(type, DirectiveTypeEnum.META_TAG); 
        assertEquals(MetaTagsDirectiveEnum.comment.getNorm(), directive.getNorm());
        assertEquals(MetaTagsDirectiveEnum.comment.getAbbr(),directive.getAbbr());
    }
    
    @Test
    public void testTitle_Abbr_Directive() { 
        
        directiveLocation = new DirectiveLocation("t", Integer.SIZE);  
        Directive directive = directiveLocation.getDirective();
        DirectiveTypeEnum type = directive.getType();
        assertEquals(type, DirectiveTypeEnum.META_TAG); 
        assertEquals(MetaTagsDirectiveEnum.title.getNorm(), directive.getNorm());
        assertEquals(MetaTagsDirectiveEnum.title.getAbbr(),directive.getAbbr());
    }
    
    @Test
    public void testGuitar_Comment_Abbr_Directive() {  
        
        directiveLocation = new DirectiveLocation("gc", Integer.SIZE);
        Directive directive = directiveLocation.getDirective();
        DirectiveTypeEnum type = directive.getType();
        assertEquals(type, DirectiveTypeEnum.META_TAG); 
        assertEquals(MetaTagsDirectiveEnum.guitarComment.getNorm(), directive.getNorm());
        assertEquals(MetaTagsDirectiveEnum.guitarComment.getAbbr(),directive.getAbbr());
    } 
    
}
