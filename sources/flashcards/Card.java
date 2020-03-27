package flashcards;

public class Card
{
    private String prompt;
    private String response;

    public Card ()
    {
    }

    public String getPrompt () { return prompt; }
    public void setPrompt (String value) { prompt = value; }

    public String getResponse () { return response; }
    public void setResponse (String value) { response = value; }
}
