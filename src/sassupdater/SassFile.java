package sassupdater;

import java.io.File;

/**
 * Created by stephen on 5/29/16.
 */
public class SassFile {

    File sassfilename;
    File cssfilename;

    public SassFile(File sassfname, File cssfname) {
        sassfilename = sassfname;
        cssfilename = cssfname;
    }

    public boolean update() {
        System.out.println("Got it");
        return true;
    }
}
