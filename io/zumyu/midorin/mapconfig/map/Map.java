package io.zumyu.midorin.mapconfig.map;

import io.zumyu.midorin.mapconfig.util.Logger;
import io.zumyu.midorin.mapconfig.exception.config.TeamNotExistException;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class Map
{
   private File mapFile;
   private String mapName;
   private String worldName;
   private Location worldSpawnPoint;
   private List<String> teams = new ArrayList<>();
   private java.util.Map<String, Location> teamSpawnPoints = new HashMap<>();

   public Map(File mapFile)
   {
      this.mapFile = mapFile;

      if (mapFile.exists())
      {
         final FileConfiguration mapFileConfig = YamlConfiguration.loadConfiguration(mapFile);

         if (mapFileConfig.contains("mapName") &&
                 mapFileConfig.contains("worldName") &&
                 mapFileConfig.contains("worldSpawnPoint") &&
                 mapFileConfig.contains("team") &&
                 mapFileConfig.contains("teamSpawnPoint"))
         {
            mapName = mapFileConfig.getString("mapName");
            worldName = mapFileConfig.getString("worldName");
            worldSpawnPoint = (Location) mapFileConfig.get("worldSpawnPoint");
            teams = mapFileConfig.getStringList("team");

            for (String team : teams)
            {
               if (mapFileConfig.contains("teamSpawnPoint." + team))
               {
                  teamSpawnPoints.put(team, (Location) mapFileConfig.get("teamSpawnPoint." + team));
               }
            }
         }
         else
         {
            Logger.log(Level.INFO, this.mapFile.getName() + " の記述に問題があります");
         }
      }
   }

   /**
    * マップ名取得
    * @return mapName
    */
   public String getMapName()
   {
      return mapName;
   }

   /**
    * ワールド名取得
    * @return worldName
    */
   public String getWorldName()
   {
      return worldName;
   }

   /**
    * 初期スポーン座標取得
    * @return worldSpawnPoint
    */
   public Location getWorldSpawnPoint()
   {
      return worldSpawnPoint;
   }

   /**
    * チームのリスト取得
    * @return teams
    */
   public List<String> getTeams()
   {
      return teams;
   }

   /**
    * チームのスポーン座標取得
    * @param team
    * @return teamSpawnPoint
    * @throws TeamNotExistException
    */
   public Location getTeamSpawnPoint(String team) throws TeamNotExistException
   {
      if (teamSpawnPoints.containsKey(team)) return teamSpawnPoints.get(team);
      else throw new TeamNotExistException(this, team);
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Map map = (Map) o;
      return mapFile.equals(map.mapFile) &&
              mapName.equals(map.mapName) &&
              worldName.equals(map.worldName) &&
              worldSpawnPoint.equals(map.worldSpawnPoint) &&
              teams.equals(map.teams) &&
              teamSpawnPoints.equals(map.teamSpawnPoints);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(mapFile, mapName, worldName, worldSpawnPoint, teams, teamSpawnPoints);
   }

   public class Builder
   {
      private File mapFile;
      private String mapName;
      private String worldName;
      private Location worldSpawnPoint;
      private List<String> teams = new ArrayList<>();
      private java.util.Map<String, Location> teamSpawnPoints = new HashMap<>();

      public void setMapName(String mapName)
      {
         this.mapName = mapName;
      }

      public void setWorldName(String worldName)
      {
         this.worldName = worldName;
      }

      public void setWorldSpawnPoint(Location worldSpawnPoint)
      {
         this.worldSpawnPoint = worldSpawnPoint;
      }

      public boolean setTeam(String teamName, Location spawnPoint)
      {
         if (!teams.contains(teamName))
         {
            teams.add(teamName);
            if (teamSpawnPoints.containsKey(teamName)) teamSpawnPoints.remove(teamName);
            teamSpawnPoints.put(teamName, spawnPoint);

            return true;
         }
         else return false;
      }
   }
}
