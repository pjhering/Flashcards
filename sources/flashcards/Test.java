package flashcards;

import java.lang.reflect.Method;

public class Test
{
    private static final Class[] TESTS = new Class[]
    {
        TestMain.class,
        TestFileInfo.class,
        TestDeck.class
    };
    private static final Object[] ARGS = new Object[0];

    public static void main(String[] args)
    {
        for (Class c : TESTS)
        {
            try
            {
                Object o = c.newInstance ();
                Method[] ms = c.getMethods ();

                System.out.print ("\n#################### ");
                System.out.print (c.getName ());
                System.out.println (" ####################\n");

                for (Method m : ms)
                {
                    String n = m.getName ();

                    if (n.startsWith ("test"))
                    {
                        System.out.print ("method ");
                        System.out.print (n);
                        System.out.print (" - ");

                        try
                        {
                            m.invoke (o, ARGS);
                            System.out.println ("passed");
                        }
                        catch (Exception ex)
                        {
                            System.out.println ("failed");
                            ex.printStackTrace ();
                        }
                        System.out.println ();
                    }
                }
                System.out.println ();
            }
            catch (Exception ex)
            {
                ex.printStackTrace ();
            }
        }
    }
}
