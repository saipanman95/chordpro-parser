package com.mdrsolutions.chordpro.parser.factories.producers;

import com.mdrsolutions.chordpro.parser.enums.Directive;
import com.mdrsolutions.chordpro.parser.enums.MetaTagsDirectiveEnum;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class builds a SimpleTextSongline pre-curser String object (which would be
 * used in a SimpleTextSongLine object) that iterates through SongPro
 * MetaTagDirectives and parses a song line into a simplified Text version of
 * that meta tag directive. All meta tags are converted to CAPS to make
 * distinguishable from remaining text. All other tag sectional tag directives
 * are explicitly removed, since these require some sort of font color, font
 * style or background color not avaliable to simple text version of songpro
 * file.
 *
 * @author michaelrodgers
 */
public class SimpleTextMetaTagDirectiveBuilder {

    private static final String META_TAGS_DIRECT_REGEX_PATTERN = "\\{.*:(.*?)\\}";
    private static final String SECTIONAL_DIRECT_REGEX_PATTERN = "\\{(.*?)\\}.*?\\{(.*?)\\}";

    private static final int META_TAG = 1;
    private static final int SECTIONAL_TAG = 2;
    
    public String build(String songLine) {
        MetaTagsDirectiveEnum[] values = MetaTagsDirectiveEnum.values();

        for (Directive d : values) {

            SongLineMatchedObject lineMatchedObject = songLinePatterMatcherTransformer(songLine, d);

            if (lineMatchedObject.getMatched()) {
                songLine = lineMatchedObject.getSongLine();
            }
        }
        return eliminateRemainingDirectives(songLine, SECTIONAL_TAG);
    }

    private SongLineMatchedObject songLinePatterMatcherTransformer(String songLine, Directive d) {
        String modifiedSongLine = songLine;
        Pattern pattern = Pattern.compile(META_TAGS_DIRECT_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);

        Map<String, String> directiveWrapper = d.directiveWrapper("{", ":");
        String abbr = directiveWrapper.get(Directive.ABBR);
        String norm = directiveWrapper.get(Directive.NORM);

        SongLineMatchedObject songLineMatchedObject = new SongLineMatchedObject(Boolean.FALSE, songLine);
        while (matcher.find()) {

            String original = matcher.group(0); 
            String without;
            
            if (songLine.contains(abbr)) { 
                without = original.replace(abbr, "").replace("}", ""); 
                modifiedSongLine = modifiedSongLine.replace(original, without.toUpperCase().trim());
            } else if (songLine.contains(norm)) {
                without = original.replace(norm, "").replace("}", ""); 
                modifiedSongLine = modifiedSongLine.replace(original, without.toUpperCase().trim());
            }

            songLineMatchedObject = new SongLineMatchedObject(Boolean.TRUE, modifiedSongLine);
        }
        return songLineMatchedObject;
    }

    private String eliminateRemainingDirectives(String songLine, int MetaTagOrSectionalTag) {
        
        Pattern pattern = Pattern.compile((MetaTagOrSectionalTag == SECTIONAL_TAG) ? SECTIONAL_DIRECT_REGEX_PATTERN : META_TAGS_DIRECT_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);

        while (matcher.find()) {
            String sectWithoutStart = matcher.group(1);
            String sectWithoutEnd = matcher.group(2);
            songLine = songLine.replace("{" + sectWithoutStart + "}", "").replace("{" + sectWithoutEnd + "}", "");
        }

        return songLine;
    }
}
