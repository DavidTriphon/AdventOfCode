package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.asteroids.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day10B
{
   private static final URL INPUT_FILE_LOC = Day10B.class.getResource("input/input10.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      AsteroidMap map = AsteroidMap.fromString(fileString);
      
      map.setStationPosition(map.mostVisibleAsteroid());
      
      int count = 1;
      
      while (map.getAsteroidLocations().size() > 1)
      {
         Map.Entry <Integer, Integer> pos = map.removeNextAsteroid();
         System.out.printf("%03d: (%d, %d)\n", count++, pos.getKey(), pos.getValue());
         
         if (count == 201)
            return pos.getKey() * 100 + pos.getValue();
      }
      
      throw new IllegalStateException("not enough asteroids. count=" + (count - 1));
   }
}
