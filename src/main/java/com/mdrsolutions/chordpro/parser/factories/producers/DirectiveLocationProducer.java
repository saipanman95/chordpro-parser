package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.models.DirectiveLocation;
import com.mdrsolutions.chordpro.parser.models.Location;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DirectiveLocationProducer<LO extends Location> implements LocationProducer<LO> {

    private static final String CHORD_PRO_DIRECTIVE_REGEX_PATTERN = "\\{(.*?)\\}";
    
    @Override
    public List<Location> produce(String songLine) {
        Pattern pattern = Pattern.compile(CHORD_PRO_DIRECTIVE_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);

        List<Location> directives = new LinkedList<>();

        int directiveLocation = 0;
        
        while (matcher.find()) {
            String rawDirective = matcher.group(0);
            directiveLocation = songLine.indexOf(rawDirective, directiveLocation);
            directives.add(new DirectiveLocation(rawDirective, directiveLocation));
            
            //adding one so it keeps looking forward on next indexOf method
            directiveLocation++;
        }

        return  directives;
    }
    
}
