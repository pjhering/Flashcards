package flashcards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    static final String HOME = System.getProperty ("user.home");
    static final File appDir = new File (HOME, ".flashcards/");
    static final File importDir = new File (appDir, "imports/");
    static final File practiceDir = new File (appDir, "practice/");

    public static void main(String[] args)
    {
        List<FileInfo> importList = createFileInfoList (importDir, FileType.IMPORTED);
        List<FileInfo> practiceList = createFileInfoList (practiceDir, FileType.PRACTICE);

        AppMainUI ui = new AppMainUI (importList, practiceList);
        ui.start ();
    }

    static List<FileInfo> createFileInfoList (File dir, FileType type)
    {
        if (!dir.exists ())
        {
            dir.mkdirs ();
        }
        File[] files = dir.listFiles ((File f, String s) -> {
            return s.endsWith (".json");
        });
        List<FileInfo> list = new ArrayList<> ();

        for (File f : files)
        {
            list.add (new FileInfo (f, type));
        }

        return list;
    }
}
