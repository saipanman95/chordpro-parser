package com.mdrsolutions.chordpro.parser;

/**
 *
 * @author mrodgers
 */
public class Main {

    private static final String PATH = "//Users/mrodgers/Downloads/CameToMyRescue.pro";
    private static final String SONG
            = "{c: Verse 1: } {soh} (Unison & Harmony) {eoh}\n"
            + "{soi}(das){eoi} Bless the[C] Lord, O my[G] {soh}(now dance){eoh}soul,[D/F#] O my [Em] soul,\n"
            + "[C]Worship His ho[G]ly n[Dsus4]ame.    [D]\n"
            + "Sing like [C]never be[Em]fore, [C]   [D]O m[Em]y soul.\n"
            + "I'll[C] worship Your ho[D]ly na[C/G]me.   [G]";

    public static void main(String[] args) {
        HtmlSongParser parser = new HtmlSongParser();
//        System.out.println(parser.parse(SONG, true));
        System.out.println("----");
        System.out.println("Utilizing SimpleSongTextParser class to parse file from given path \n\n" + parser.parse(PATH));
    }

}
