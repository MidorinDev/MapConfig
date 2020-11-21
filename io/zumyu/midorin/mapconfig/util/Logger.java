package io.zumyu.midorin.mapconfig.util;

import io.zumyu.midorin.mapconfig.MapConfigPlugin;

import java.util.logging.Level;

public final class Logger
{
   public static void log(Level level, String msg)
   {
      MapConfigPlugin.getInstance().getLogger().log(level, msg);
   }
}
