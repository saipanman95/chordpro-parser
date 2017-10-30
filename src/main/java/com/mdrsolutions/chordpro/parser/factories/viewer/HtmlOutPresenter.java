package com.mdrsolutions.chordpro.parser.factories.viewer;

import com.mdrsolutions.chordpro.parser.enums.HtmlWrapper;
import com.mdrsolutions.chordpro.parser.models.HtmlSongLine;
import java.util.Collection; 

public class HtmlOutPresenter implements Presenter {

    private final Collection<HtmlSongLine> htmlSongLines;
    private final String title;

    public HtmlOutPresenter(Collection<HtmlSongLine> htmlSongLines, String title) {
        this.htmlSongLines = htmlSongLines;
        this.title = title;
    }

    @Override
    public String present() {
        StringBuilder sb = new StringBuilder();
        
        htmlSongLines.forEach((tsl) -> {
            if (isNotEmpty(tsl.getChordLine())) {
                sb.append(tsl.getChordLine()).append("\n");
            }
            sb.append(tsl.getSongLine()).append("\n");
        }); 
        String pre = HtmlWrapper.pre.wrap(sb.toString(), null);
        String body = HtmlWrapper.body.wrap(pre, null);
        String headTitle = HtmlWrapper.title.wrap(this.title, null);
        String metaEncoding = HtmlWrapper.meta.wrap("", null);
        String head = HtmlWrapper.head.wrap(headTitle +metaEncoding, null);
        String html = HtmlWrapper.html.wrap(head + body, null);
        
        return html;
    }
    
    public boolean isNotEmpty(java.lang.String txt){
        
        if(null == txt){
            return false;
        }
        if(txt.isEmpty()){
            return false;
        }
        return true;
    }

}
