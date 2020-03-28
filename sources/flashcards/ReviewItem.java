package flashcards;

import java.util.Date;

public class ReviewItem
{
    public static final long ONE_DAY          = 86400000L;
    public static final long TWO_DAYS         = ONE_DAY * 2;
    public static final long FOUR_DAYS        = TWO_DAYS * 2;
    public static final long EIGHT_DAYS       = FOUR_DAYS * 2;
    public static final long SIXTEEN_DAYS     = EIGHT_DAYS * 2;
    public static final long THIRTYTWO_DAYS   = SIXTEEN_DAYS * 2;
    public static final long SIXTYFOUR_DAYS   = THIRTYTWO_DAYS * 2;
    public static final long MAXIMUM_DAYS     = SIXTYFOUR_DAYS * 2;

    private Card card;
    private Date lastReviewDate;
    private Date nextReviewDate;
    private int consecutiveCorrectResponses;
    private long interval;

    public ReviewItem ()
    {
    }

    public ReviewItem (Card card)
    {
        this.card = card;
        this.consecutiveCorrectResponses = 0;
        this.interval = 0L;
    }

    public Card getCard () { return card; }
    public void setCard (Card value) { card = value; }

    public Date getLastReviewDate () { return lastReviewDate; }
    public void setLastReviewDate (Date value) { lastReviewDate = value; }

    public Date getNextReviewDate () { return nextReviewDate; }
    public void setNextReviewDate (Date value) { nextReviewDate = value; }

    public int getConsecutiveCorrectResponses () { return consecutiveCorrectResponses; }
    public void setConsecutiveCorrectResponses (int value) { consecutiveCorrectResponses = value; }

    public long getInterval () { return interval; }
    public void setInterval (long value) { interval = value; }
}
