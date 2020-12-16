package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day06A
{
   private static final URL INPUT_FILE_LOC = Day06A.class.getResource("input/input6.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      HashMap <String, String> solarMap = new HashMap <>();
   
      for (String line : ReaderUtil.parseFileToList(INPUT_FILE_LOC))
      {
         String[] bodies = line.split("\\)");
      
         solarMap.put(bodies[1], bodies[0]);
      }
   
      HashMap <String, Integer> levels = new HashMap <>();
      int totalLevels = 0;
   
      for (String body : solarMap.keySet())
      {
         totalLevels += getLevel(solarMap, levels, body);
      }
      
      return totalLevels;
   }
   
   
   private static int getLevel(
      HashMap <String, String> solarMap, HashMap <String, Integer> levels, String body)
   {
      if (levels.containsKey(body))
         return levels.get(body);
      
      if (!solarMap.containsKey(body))
      {
         levels.put(body, 0);
         return 0;
      }
      
      String parentBody = solarMap.get(body);
      int parentLevel = getLevel(solarMap, levels, parentBody);
      levels.put(body, parentLevel + 1);
      return parentLevel + 1;
   }
}
