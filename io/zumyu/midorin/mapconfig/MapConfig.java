package io.zumyu.midorin.mapconfig;

import io.zumyu.midorin.mapconfig.exception.GameExistException;
import io.zumyu.midorin.mapconfig.map.Map;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class MapConfig
{
   private final String gameName;
   private final File mapFolder;
   private final java.util.Map<String, Map> maps = new HashMap<>();

   public MapConfig(String gameName)
   {
      this.gameName = gameName;
      mapFolder = new File(MapConfigPlugin.getInstance().getDataFolder() + "/" + this.gameName + "/map");
      mapFolder.mkdirs();
   }

   /**
    * ゲーム名取得
    * @return gameName
    */
   public String getGameName()
   {
      return gameName;
   }

   public File getMapFolder()
   {
      return mapFolder;
   }

   public Map getMap(String mapName)
   {
      return maps.get(mapName);
   }

   private void loadConfigs()
   {
      for (File config : mapFolder.listFiles())
      {

      }
   }
}
