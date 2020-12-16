package davidt.aoc.years.y2019.days;

import davidt.aoc.years.y2019.repair.*;

import java.io.*;


public class Day15A
{
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      RepairDroid droid = new RepairDroid();
      
      droid.autoFindOxygen();
      
      System.out.println(droid.getFormattedMap());
      System.out.println(droid.getMap().countOf(RepairDroid.TILE_PATH));
      
      return droid.getMap().countOf(RepairDroid.TILE_PATH);
   }
}
