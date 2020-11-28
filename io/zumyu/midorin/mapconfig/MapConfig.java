package io.zumyu.midorin.mapconfig;

import io.zumyu.midorin.mapconfig.util.LogHelper;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

public class MapConfig
{
   private final Plugin plugin;
   private final File mapFolder;
   private final Map<String, io.zumyu.midorin.mapconfig.map.Map> maps = new TreeMap<>();

   public MapConfig(Plugin plugin)
   {
      this.plugin = plugin;
      mapFolder = new File(plugin.getDataFolder() + "/map");
      if (!mapFolder.exists()) mapFolder.mkdirs();
      loadMapConfigs();
   }

   /**
    * マップ取得
    * @param mapName マップ名
    * @return map
    */
   public io.zumyu.midorin.mapconfig.map.Map getMap(String mapName)
   {
      return maps.get(mapName);
   }

   /**
    * ロード済みのマップをすべて取得
    * @return maps
    */
   public Map getMaps()
   {
      return maps;
   }

   /**
    * フォルダー内のymlファイルを読み込む
    */
   private void loadMapConfigs()
   {
      LogHelper.log(Level.INFO, "-----------[ " + plugin.getName() + " ]-----------");

      for (File file : Objects.requireNonNull(mapFolder.listFiles()))
      {
         if (file.getName().endsWith(".yml"))
         {
            LogHelper.log(Level.INFO, "Found: " + file.getName());
            io.zumyu.midorin.mapconfig.map.Map map = null;
            try
            {
               map = new io.zumyu.midorin.mapconfig.map.Map(file);
               maps.put(map.getMapName(), map);
            }
            catch (Exception e)
            {
               LogHelper.log(Level.INFO, "Failed: " + file.getName());
               e.printStackTrace();
            }
         }
      }
      LogHelper.log(Level.INFO, "Loaded: " + maps.keySet().toString());
      LogHelper.log(Level.INFO, "---------------------------------------");
   }
}
