package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main
{

    static final String HOME = System.getProperty ("user.home");
    static final File appDir = new File (HOME, ".flashcards/");
    static final File importDir = new File (appDir, "imports/");
    static final File practiceDir = new File (appDir, "practice/");

    public static void main(String[] args)
    {
        try
        {
            List<FileInfo> importList = createFileInfoList (importDir, FileType.IMPORTED);
            List<FileInfo> practiceList = createFileInfoList (practiceDir, FileType.PRACTICE);

            AppMainUI ui = new AppMainUI (importList, practiceList);
            ui.start ();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog (null, ex, "ERROR", JOptionPane. ERROR_MESSAGE);
        }
    }

    static List<FileInfo> createFileInfoList (File dir, FileType type) throws Exception
    {
        if (!dir.exists ())
        {
            dir.mkdirs ();

            File readme = new File (dir, "README.txt");
            try (FileWriter writer = new FileWriter (readme))
            {
                writer.write ("WARNING!\n");
                writer.write ("Do not edit or delete the files in this folder.\n");
                writer.write ("They are for use with the flashcards application.\n");
                writer.write ("Modifying these files could make them unusable.\n");
                writer.write ("\n");
                writer.flush ();
            }
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
