package flashcards;

import java.util.ArrayList;
import java.util.List;

public class Practice
{
    private String title;
    private String author;
    private String fileName;
    private List<ReviewItem> reviewItems;

    public Practice ()
    {
        reviewItems = new ArrayList<> ();
    }

    public Practice (Deck deck, String file)
    {
        this ();
        this.title = deck.getTitle ();
        this.author = deck.getAuthor ();
        this.fileName = file;

        for (Card card : deck.getCards ())
        {
            ReviewItem item = new ReviewItem (card);
            reviewItems.add (item);
        }
    }

    @Override
    public String toString ()
    {
        String step1 = fileName.substring (0, fileName.lastIndexOf (".json"));
        String step2 = step1.replace ('-', ' ');
        return step2;
    }

    public String getTitle () { return title; }
    public void setTitle (String value) { title = value; }

    public String getAuthor () { return author; }
    public void setAuthor (String value) { author = value; }

    public String getFileName () { return fileName; }
    public void setFileName (String value) { fileName = value; }

    public List<ReviewItem> getReviewItems () { return reviewItems; }
    public void setReviewItems (List<ReviewItem> value) { reviewItems = value; }
}
