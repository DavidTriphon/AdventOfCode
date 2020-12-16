package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.solarMap.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day06B
{
   private static final URL INPUT_FILE_LOC = Day06B.class.getResource("input/input6.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      HashMap <String, String> orbitMap = new HashMap <>();
   
      for (String line : ReaderUtil.parseFileToList(INPUT_FILE_LOC))
      {
         String[] bodies = line.split("\\)");
      
         orbitMap.put(bodies[1], bodies[0]);
      }
   
      SolarMap map = new SolarMap(orbitMap);
   
      return map.getDistanceBetween("YOU", "SAN") - 2;
   }
}
