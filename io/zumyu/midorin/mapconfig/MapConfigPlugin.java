package io.zumyu.midorin.mapconfig;

import io.zumyu.midorin.mapconfig.util.LogHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class MapConfigPlugin extends JavaPlugin
{
   private static MapConfigPlugin instance;

   public void onEnable()
   {
      instance = this;
      LogHelper.log(Level.INFO, getInstance().getName() + " enabled");
   }

   public void onDisable()
   {
      LogHelper.log(Level.INFO, getInstance().getName() + " disabled");
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
