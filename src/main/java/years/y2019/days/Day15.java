package years.y2019.days;

import years.y2019.repair.*;

import java.io.*;


public class Day15
{
   public static void main(String... args) throws IOException
   {
      RepairDroid droid = new RepairDroid();
      
      droid.autoFindOxygen();
      
      System.out.println(droid.getFormattedMap());
      System.out.println(droid.getMap().count().get(RepairDroid.TILE_PATH));
   }
}
