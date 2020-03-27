package flashcards;

import java.io.File;

public class FileInfo
{
    private final File file;
    private final FileType type;

    public FileInfo (File file, FileType type)
    {
        this.file = file;
        this.type = type;
    }

    public File getFile () { return file; }

    public FileType getType () { return type; }

    @Override
    public String toString ()
    {
        return file.getName ();
    }
}
