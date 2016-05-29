package sassupdater;

/**
 * Created by stephen on 5/29/16.
 */
public class SassFile {

    String sassfilename;
    String cssfilename;

    public SassFile(String sassfname, String cssfname) {
        sassfilename = sassfname;
        cssfilename = cssfname;
    }

    public boolean update() {
        return true;
    }
}
