package com.mdrsolutions.chordpro.parser.factories.viewer;

import com.mdrsolutions.chordpro.parser.models.SimpleTextSongLine;
import java.util.Collection; 

public class SystemOutPresenter<String> implements Presenter<String> {

    private final Collection<SimpleTextSongLine> simpleTextSongLines;

    public SystemOutPresenter(Collection<SimpleTextSongLine> simpleTextSongLines) {
        this.simpleTextSongLines = simpleTextSongLines;
    }

    @Override
    public String present() {
        StringBuilder sb = new StringBuilder();
        simpleTextSongLines.forEach((tsl) -> {
            if (isNotEmpty(tsl.getChordLine())) {
                sb.append(tsl.getChordLine()).append("\n");
            }
            sb.append(tsl.getSongLine()).append("\n");
        }); 
        return (String) sb.toString();
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
