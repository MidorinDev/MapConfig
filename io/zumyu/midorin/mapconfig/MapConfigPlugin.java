package io.zumyu.midorin.mapconfig;

import io.zumyu.midorin.mapconfig.util.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class MapConfigPlugin extends JavaPlugin
{
   private static MapConfigPlugin instance;

   public void onEnable()
   {
      instance = this;
      Logger.log(Level.INFO, getInstance().getName() + "started");
   }

   public void onDisable()
   {

   }

   /**
    * プラグインインスタンスを取得
    * @return instance
    */
   public static MapConfigPlugin getInstance()
   {
      return instance;
   }
}
