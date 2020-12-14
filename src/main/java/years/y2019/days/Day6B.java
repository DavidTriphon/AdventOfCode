package years.y2019.days;

import util.*;
import years.y2019.solarMap.*;

import java.io.*;
import java.util.*;


public class Day6B
{
   private static final String inputFileLoc =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input6.txt";
   
   
   public static void main(String... args) throws IOException
   {
      BufferedReader inputReader = new BufferedReader(new FileReader(inputFileLoc));
      
      String line;
      
      HashMap <String, String> orbitMap = new HashMap <>();
      
      while ((line = inputReader.readLine()) != null)
      {
         String[] bodies = line.split("\\)");
         
         orbitMap.put(bodies[1], bodies[0]);
      }
      
      SolarMap map = new SolarMap(orbitMap);
      
      System.out.println(map.getDistanceBetween("YOU", "SAN") - 2);
   }
}
