package PaooGame;

public class Singletone {

    protected static Singletone instance;
    protected Singletone()
    {

    }

    public static Singletone getInstance()
    {
        if (instance==null)
            return new Singletone();
        return instance;
    }

}
