package sassupdater;

import java.io.File;
import java.io.IOException;

/**
 * Created by stephen on 5/29/16.
 */
public class SassFile {

    File sassfilename;
    File cssfilename;

    public SassFile(File sassfname, File cssfname) {
        sassfilename = sassfname;
        cssfilename = cssfname;
        FileList.addFile(this);
    }

    public boolean update() throws IOException {
        Main.compileSassFile(this);
        return true;
    }

    public void remove() {
        FileList.removeFile(this);
    }
}
