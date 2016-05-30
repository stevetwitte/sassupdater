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
        Main.updateSassFile(this);
        return true;
    }

    public boolean force() throws IOException {
        Main.forceSassFile(this);
        return true;
    }

    public void remove() {
        FileList.removeFile(this);
    }

    public String getDisplayName() {
        if (this.sassfilename.getAbsoluteFile().toString().length() > 18) {
            return "..." + this.sassfilename.getAbsoluteFile().toString().substring(Math.max(0, 20));
        } else {
            return this.sassfilename.getAbsoluteFile().toString();
        }
    }
}
