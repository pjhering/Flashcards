package flashcards;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class AppMainUI extends JFrame
{
    private final AppMainActions actions;
    JMenuItem fileImportItem;
    JMenuItem fileDeleteItem;
    JMenuItem fileExitItem;
    JMenuItem practiceDeleteItem;
    JMenuItem practiceCreateItem;
    JMenuItem practiceReviewItem;
    JList<FileInfo> leftList;
    DefaultListModel<FileInfo> leftListModel;

    public AppMainUI (List<FileInfo> iList, List<FileInfo> pList)
    {
        super ("flashcards");
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);

        initializeMenuBar ();
        initializeContentPane (iList, pList);

        actions = new AppMainActions (this);
    }

    private void initializeContentPane (List<FileInfo> iList, List<FileInfo> pList)
    {
        JPanel content = new JPanel (new GridLayout (1, 2, 10, 10));
        content.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));

        leftListModel = new DefaultListModel<> ();
        iList.forEach ((i) -> { leftListModel.addElement (i); });
        leftList = new JList<FileInfo> (leftListModel);
        leftList.setSelectionMode (SINGLE_SELECTION);
        JPanel leftLabelPanel = new JPanel (new GridLayout (1, 1));
        leftLabelPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.GRAY, 1), "imports"));
        leftLabelPanel.add (new JScrollPane (leftList));
        content.add (leftLabelPanel);

        JList<FileInfo> rightList = new JList<FileInfo> (pList.toArray (new FileInfo[pList.size ()]));
        rightList.setSelectionMode (SINGLE_SELECTION);
        JPanel rightLabelPanel = new JPanel (new GridLayout (1, 1));
        rightLabelPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.GRAY, 1), "practice"));
        rightLabelPanel.add (new JScrollPane (rightList));
        content.add (rightLabelPanel);

        setContentPane (content);
    }

    private void initializeMenuBar ()
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
    }

    public void start ()
    {
        pack ();
        setLocationRelativeTo (null);
        setVisible (true);
    }
}
