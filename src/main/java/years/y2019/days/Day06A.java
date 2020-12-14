package years.y2019.days;

import util.*;

import java.io.*;
import java.util.*;


public class Day06A
{
   private static final String inputFileLoc =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input6.txt";
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      BufferedReader inputReader = new BufferedReader(new FileReader(inputFileLoc));
      
      String line;
      
      HashMap <String, String> solarMap = new HashMap <>();
      
      while ((line = inputReader.readLine()) != null)
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
