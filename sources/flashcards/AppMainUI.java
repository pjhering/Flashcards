package flashcards;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class AppMainUI extends JFrame
{
    private final AppMainActions actions;
    JMenuItem fileImportItem;
    JMenuItem fileDeleteItem;
    JMenuItem fileExitItem;
    JMenuItem practiceDeleteItem;
    JMenuItem practiceCreateItem;
    JMenuItem practiceReviewItem;

    public AppMainUI (List<FileInfo> iList, List<FileInfo> pList)
    {
        super ("flashcards");
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        initialize ();
        actions = new AppMainActions (this);
    }

    private void initialize ()
    {
        JMenuBar menubar = new JMenuBar ();
        JMenu fileMenu = new JMenu ("file");
        fileImportItem = new JMenuItem ("import");
        fileDeleteItem = new JMenuItem ("delete");
        fileExitItem = new JMenuItem ("quit");
        fileMenu.add (fileImportItem);
        fileMenu.add (fileDeleteItem);
        fileMenu.add (fileExitItem);
        menubar.add (fileMenu);
        JMenu practiceMenu = new JMenu ("practice");
        practiceDeleteItem = new JMenuItem ("delete");
        practiceCreateItem = new JMenuItem ("create");
        practiceReviewItem = new JMenuItem ("review");
        practiceMenu.add (practiceDeleteItem);
        practiceMenu.add (practiceCreateItem);
        practiceMenu.add (practiceReviewItem);
        menubar.add (practiceMenu);
        setJMenuBar (menubar);

        JPanel content = new JPanel (new BorderLayout (10, 10));
        content.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));
    }

    public void start ()
    {
        pack ();
        setLocationRelativeTo (null);
        setVisible (true);
    }
}
