package flashcards;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import static flashcards.Main.importDir;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AppMainActions
{
    private final AppMainUI ui;
    private final JFileChooser chooser;

    public AppMainActions (AppMainUI ui)
    {
        this.ui = ui;
        this.chooser = new JFileChooser ();
        this.chooser.addChoosableFileFilter (new FileNameExtensionFilter ("JSON file", "json"));

        ui.addWindowListener (new WindowAdapter (){
            @Override
            public void windowClosing (WindowEvent e){
                fileExitAction ();
            }
        });
        ui.fileImportItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                fileImportAction ();
            }
        });
        ui.fileDeleteItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                fileDeleteAction ();
            }
        });
        ui.fileExitItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                fileExitAction ();
            }
        });
        ui.practiceDeleteItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                practiceDeleteAction ();
            }
        });
        ui.practiceCreateItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                practiceCreateAction ();
            }
        });
        ui.practiceReviewItem.addActionListener (new ActionListener (){
            @Override
            public void actionPerformed (ActionEvent e){
                practiceReviewAction ();
            }
        });
    }

    private void fileExitAction ()
    {
        ui.setVisible (false);
        ui.dispose ();
        System.exit (0);
    }

    private void fileImportAction ()
    {
        // show JFileChooser and get file
        int value = chooser.showOpenDialog (ui);
        if (value != JFileChooser.APPROVE_OPTION) return;
        File file = chooser.getSelectedFile ();

        // create reader for the imported file
        try (FileReader reader = new FileReader (file))
        {
            // validate by deserializing a Deck object with gson
            Gson gson = new Gson ();
            Deck deck = gson.fromJson (reader, Deck.class);

            // create a new file in the imports folder
            File importFile = new File (importDir, file.getName ());
            if (importFile.exists ())
            {
                showMessageDialog (ui.leftList, file.getName () + " has already been imported.", "Warning", PLAIN_MESSAGE);
                return;
            }

            // serialize the deck object into the new file
            try (FileWriter writer = new FileWriter (importFile))
            {
                JsonWriter json = gson.newJsonWriter (writer);
                gson.toJson (deck, Deck.class, json);
            }

            // create a new FileInfo object from the new file
            FileInfo fi = new FileInfo (importFile, FileType.IMPORTED);

            // add the newly created FileInfo to the imports list
            ui.leftListModel.addElement (fi);

            // provide feedback to the user
            showMessageDialog (ui.leftList, "Imported " + importFile.getName (), "Message", PLAIN_MESSAGE);
        }
        catch (Exception ex)
        {
            showMessageDialog (ui, "ERROR: invalid file - " + file.getName ());
        }
    }

    private void fileDeleteAction ()
    {
        System.out.println ("fileDeleteAction");
    }

    private void practiceDeleteAction ()
    {
        System.out.println ("practiceDeleteAction");
    }

    private void practiceCreateAction ()
    {
        System.out.println ("practiceCreateAction");
    }

    private void practiceReviewAction ()
    {
        System.out.println ("practiceReviewAction");
    }
}
