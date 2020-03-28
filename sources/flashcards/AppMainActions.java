package flashcards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import static flashcards.Main.importDir;
import static flashcards.Main.practiceDir;
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
        {   // validate by deserializing a Deck object with gson
            Gson gson = new GsonBuilder ()
                .setPrettyPrinting ()
                .serializeNulls ()
                .create ();
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
        // get selected FileInfo
        FileInfo fi = ui.leftList.getSelectedValue ();
        if (fi == null) return;

        // attempt to delete file
        File f = fi.getFile ();
        if (f.delete ())
        {   // remove FileInfo from imports list
            ui.leftListModel.removeElement (fi);

            // provide feedback to the user
            showMessageDialog (ui.leftList, "Deleted " + f.getName ());
        }
        else
        {   // provide feedback to the user
            showMessageDialog (ui.leftList, "Oops... That didn't work.");
        }
    }

    private void practiceCreateAction ()
    {
        // get selected FileInfo from the imports list
        FileInfo fi = ui.leftList.getSelectedValue ();
        if (fi == null) return;

        try (FileReader reader = new FileReader (fi.getFile ()))
        {   // deserialize Deck object
            Gson gson = new GsonBuilder ()
                .setPrettyPrinting ()
                .serializeNulls ()
                .create ();
            Deck deck = gson.fromJson (reader, Deck.class);

            // determine name for and create new file in practice folder
            int count = 1;
            File file;
            while (true)
            {
                String name = deck.getTitle () + "-" + count + ".json";
                file = new File (practiceDir, name);
                if (!file.exists ())
                {
                    break;
                }
                else
                {
                    count += 1;
                }
            }
            // create new Practice object with Deck and File
            Practice practice = new Practice (deck, file.getName ());

            try (FileWriter writer = new FileWriter (file))
            {
                // serialize the Practice object
                JsonWriter json = gson.newJsonWriter (writer);
                gson.toJson (practice, Practice.class, json);

                // add Practice object to practice list
                ui.rightListModel.addElement (practice);

                // provide feedback to user
                showMessageDialog (ui.rightList, "Created " + practice.toString ());
            }
        }
        catch (Exception ex)
        {
            showMessageDialog (ui.leftList, "Error: " + ex.getMessage ());
        }
    }

    private void practiceDeleteAction ()
    {
        // get selected practice item from list
        Practice p = ui.rightList.getSelectedValue ();
        if (p == null) return;

        // create file
        File f = new File (practiceDir, p.getFileName ());

        // delete file
        if (f.delete ())
        {
            // remove practice item from list
            ui.rightListModel.removeElement (p);

            // provide feedback to user
            showMessageDialog (ui.rightList, "Deleted " + p.toString ());
        }
        else
        {
            showMessageDialog (ui.rightList, "Oops... That didn't work.");
        }
    }

    private void practiceReviewAction ()
    {
        System.out.println ("practiceReviewAction");
    }
}
