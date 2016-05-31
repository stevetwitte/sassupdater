package sassupdater;

import java.io.File;
import java.io.IOException;

/**
 * SassFile
 * attributes of type File sassfilename and cssfilename
 */
public class SassFile {

    File sassfilename;
    File cssfilename;

    /**
     * constructor also adds object to FileList
     * @param sassfname File
     * @param cssfname File
     */
    public SassFile(File sassfname, File cssfname) {
        sassfilename = sassfname;
        cssfilename = cssfname;
        FileList.addFile(this);
    }

    /**
     * runs the command to update the css file from sass
     * TODO: Give this a meaningful return
     * @return boolean
     * @throws IOException
     */
    public boolean update() throws IOException {
        Main.updateSassFile(this);
        return true;
    }

    /**
     * runs the command to force update the css file from sass
     * TODO: Give this a meaningful return
     * @return boolean
     * @throws IOException
     */
    public boolean force() throws IOException {
        Main.forceSassFile(this);
        return true;
    }

    /**
     * Removes only reference to object in FileList
     */
    public void remove() {
        FileList.removeFile(this);
    }

    /**
     * Returns a shortened filename for display when full path is too long
     * @return String
     */
    public String getDisplayName() {
        if (this.sassfilename.getAbsoluteFile().toString().length() > 18) {
            return "..." + this.sassfilename.getAbsoluteFile().toString().substring(Math.max(0, 20));
        } else {
            return this.sassfilename.getAbsoluteFile().toString();
        }
    }
}
