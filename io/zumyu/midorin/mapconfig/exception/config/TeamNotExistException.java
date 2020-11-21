package io.zumyu.midorin.mapconfig.exception.config;

import io.zumyu.midorin.mapconfig.map.Map;

public class TeamNotExistException extends Exception
{
   public TeamNotExistException(Map map, String team)
   {
      super("Map: " + map.getMapName() + " | Team: " + team + " does not exist.");
   }
}
