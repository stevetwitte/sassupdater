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
     * @param sassFName File
     * @param cssFName File
     */
    public SassFile(File sassFName, File cssFName) {
        sassfilename = sassFName;
        cssfilename = cssFName;
        FileList.getInstance().addFile(this);
    }

    /**
     * runs the command to update the css file from sass
     * TODO: Give this a meaningful return
     * @return String[]
     * @throws IOException
     */
    public String[] update() throws IOException {
        return SassCompiler.getInstance().updateSassFile(this);
    }

    /**
     * runs the command to force update the css file from sass
     * TODO: Give this a meaningful return
     * @return String[]
     * @throws IOException
     */
    public String[] force() throws IOException {
        return SassCompiler.getInstance().forceSassFile(this);
    }

    /**
     * Removes only reference to object in FileList
     */
    public void remove() {
        FileList.getInstance().removeFile(this);
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
