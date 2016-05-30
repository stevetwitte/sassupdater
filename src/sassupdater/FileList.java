package sassupdater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephen on 5/29/16.
 */
public class FileList {
    private static List<SassFile> modelList = new ArrayList<SassFile>();

    private static FileList filelist = new FileList();

    private FileList() {}

    public static FileList getInstance() {
        return filelist;
    }

    protected static void addFile(SassFile sfile) {
        modelList.add(sfile);
    }

    protected static void removeFile(SassFile sfile) {
        modelList.remove(sfile);
    }

    protected static SassFile getFile(int index) {
        return modelList.get(index);
    }

    protected static List<SassFile> getFileList() {
        return modelList;
    }
}
