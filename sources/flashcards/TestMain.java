package flashcards;

import java.util.List;

public class TestMain
{
    public void test_createFileInfoList ()
    {
        List<FileInfo> list = Main.createFileInfoList (Main.importDir, FileType.IMPORTED);
    }
}
