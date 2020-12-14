package years.y2019.days;

import util.*;
import years.y2019.asteroids.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day10B
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input10.txt";
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String fileString =
         Files.readString(Path.of(INPUT_FILE_LOC), StandardCharsets.US_ASCII).trim();
      
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
