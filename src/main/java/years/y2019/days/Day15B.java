package years.y2019.days;

import years.y2019.repair.*;

import java.io.*;


public class Day15B
{
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      RepairDroid droid = new RepairDroid();
      
      droid.autoExploreMap();
      
      System.out.println(droid.getFormattedMap());
      return droid.timeToOxygenation();
   }
}
