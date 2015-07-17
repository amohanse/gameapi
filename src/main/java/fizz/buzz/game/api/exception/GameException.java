// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GameException.java

package fizz.buzz.game.api.exception;


public class GameException extends RuntimeException
{

    public GameException()
    {
    }

    public GameException(String s)
    {
        super(s);
    }

    public GameException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public GameException(Throwable throwable)
    {
        super(throwable);
    }

    private static final long serialVersionUID = 1L;
}
