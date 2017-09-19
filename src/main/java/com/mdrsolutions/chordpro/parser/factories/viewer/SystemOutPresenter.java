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
            sb.append(tsl.getChordLine()).append("\n");
            sb.append(tsl.getSongLine()).append("\n"); 
        });
        System.out.println(sb.toString());
        return (String)sb.toString();
    }
    
}
