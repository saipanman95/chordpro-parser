/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdrsolutions.chordpro.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mrodgers
 */
public class Main {

    private static final String EMPTY_SPACE = "";
    private static final String SINGLE_SPACE = " ";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";
    private static final String CHORD_PRO_CHORD_REGEX_PATTERN = "\\[(.*?)\\]";
    private static final String CHORD_PRO_DIRECTIVE_REGEX_PATTERN = "\\{(.*?)\\}";
    private static final String SONG
            = "{c: Verse 1: }Bless the[C] Lord, O my[G] soul,[D/F#] O my [Em] soul,\n"
            + "[C]Worship His ho[G]ly n[Dsus4]ame.    [D]\n"
            + "Sing like [C]never be[Em]fore, [C]   [D]O m[Em]y soul.\n"
            + "I'll[C] worship Your ho[D]ly na[C/G]me.   [G]";
   static int num= 0;
    public static void main(String[] args) {
        System.out.println("num = " + num);
        System.out.println(SONG);
        List<RawSongLine> songLines = new LinkedList<>();
        List<ArrangedSongLine> arrangedSongLines = new LinkedList<>();
        
        //identify song line chord components
        songLines(SONG).forEach((songLine) -> {
            songLines.add(parseChordsDirectives(songLine));
        });

        //transform Song lines
        songLines.forEach((line) -> {
            arrangedSongLines.add(transformChordSongLines(line));
        });
        
        //print Arranged Chords and Song Lines
        arrangedSongLines.forEach((line) -> {
            System.out.println(line);
        });
        num = num +1;
        System.out.println("num = " + num);
    }

    private static List<String> songLines(String song) {
        List<String> songLines = new LinkedList<>();
        String[] split = song.split("\\n");
        songLines.addAll(Arrays.asList(split));
        return songLines;
    }

    private static List<ChordLocation> chordDetail(String songLine) {
        Pattern pattern = Pattern.compile(CHORD_PRO_CHORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);

        List<ChordLocation> chords = new LinkedList<>();

        int chordLocation = 0;
        while (matcher.find()) {
            String rawChord = matcher.group(0);
            chordLocation = songLine.indexOf(rawChord, chordLocation);
            chords.add(new ChordLocation(rawChord, chordLocation));
            
            //adding one so it keeps looking forward on next indexOf method
            chordLocation++;
        } 
        return chords;
    }
    
    private static RawSongLine parseChordsDirectives(String songLine){
        List<ChordLocation> chordDetail = chordDetail(songLine);
        List<DirectiveLocation> directiveDetail = directiveDetail(songLine);
        return new RawSongLine(songLine, chordDetail, directiveDetail);
    }
    
    private static List<DirectiveLocation> directiveDetail(String songLine) {
        Pattern pattern = Pattern.compile(CHORD_PRO_DIRECTIVE_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(songLine);

        List<DirectiveLocation> directives = new LinkedList<>();

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

    private static ArrangedSongLine transformChordSongLines(RawSongLine rawSongLine) {
        List<ChordLocation> chordLocations = rawSongLine.getChordLocations();
        String songLine = rawSongLine.getSong(); 
        
        StringBuilder chordLocBldr = new StringBuilder();
        Integer previousColumnIndex = 0;
        
        for(ChordLocation chordLocation : chordLocations){
            
            Integer columnIndex = chordLocation.getColumnIndex();
            columnIndex = (columnIndex == 0 ? 0 : columnIndex -1);
            String bracketChord = chordLocation.getChord();
            Integer bracketChordLength = bracketChord.length();
            
            String debracketedChord = debracket(bracketChord);
            
            chordLocBldr.append(addSpaces(previousColumnIndex, columnIndex)).append(debracketedChord);            
            //the new startColumnIndex as been reset to the current columnIndex
            previousColumnIndex = columnIndex + bracketChordLength +1;
        }
        
        songLine = songLine.replaceAll(CHORD_PRO_CHORD_REGEX_PATTERN, "");
        System.out.println(chordLocBldr.toString());
        System.out.println(songLine);
        
        return new ArrangedSongLine(chordLocBldr.toString(), songLine);
    }
    
    private static String debracket(String chord){
        return chord.replace(OPEN_SQUARE_BRACKET, EMPTY_SPACE).replace(CLOSE_SQUARE_BRACKET, EMPTY_SPACE);
    }
    
    private static String addSpaces(Integer previousColumnIndex, Integer startPositionColumn){
        String spaces = EMPTY_SPACE;
        if(startPositionColumn == 0){
            return spaces;
        } else {
            startPositionColumn = startPositionColumn - previousColumnIndex;
            for(int i = 0; i < startPositionColumn; i++){
                spaces = spaces + SINGLE_SPACE;
            }
        }
        return spaces;
    }
}
