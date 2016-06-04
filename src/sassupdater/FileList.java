package sassupdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton for keeping track of SassFiles
 * This really is for organization and if logic needs to be performed
 * when adding/removing SassFiles from the list
 */
public class FileList {
    private static List<SassFile> modelList = new ArrayList<SassFile>();

    private static FileList filelist = new FileList();

    private FileList() {}

    public static FileList getInstance() {
        return filelist;
    }

    protected void addFile(SassFile sfile) {
        modelList.add(sfile);
    }

    protected void removeFile(SassFile sfile) {
        modelList.remove(sfile);
    }

    protected SassFile getFile(int index) {
        return modelList.get(index);
    }

    protected List<SassFile> getFileList() {
        return modelList;
    }
}
