package flashcards;

import java.awt.event.*;

public class AppMainActions
{
    private final AppMainUI ui;

    public AppMainActions (AppMainUI ui)
    {
        this.ui = ui;
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
        System.out.println ("fileExitAction");
        ui.setVisible (false);
        ui.dispose ();
        System.exit (0);
    }

    private void fileImportAction ()
    {
        System.out.println ("fileImportAction");
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
