package io.zumyu.midorin.mapconfig.exception;

public class GameExistException extends Exception
{
   public GameExistException(String gameName)
   {
      super(gameName + "　is already using this plugin.");
   }
}
