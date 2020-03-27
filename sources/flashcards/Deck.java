package flashcards;

import java.util.ArrayList;
import java.util.List;

public class Deck
{
    private String title;
    private String author;
    private List<Card> cards;

    public Deck ()
    {
        cards = new ArrayList<> ();
    }

    public String getTitle () { return title; }
    public void setTitle (String value) { title = value; }

    public String getAuthor () { return author; }
    public void setAuthor (String value) { author = value; }

    public List<Card> getCards () { return cards; }
    public void setCards (List<Card> value) { cards = value; }
}
