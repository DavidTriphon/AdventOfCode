package years.y2019.days;

import util.*;
import years.y2019.asteroids.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Day10A
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
      
      Map.Entry <Integer, Integer> pos = map.mostVisibleAsteroid();
      
      System.out.printf("(%d,%d)\n", pos.getKey(), pos.getValue());
      System.out.println("Visibility: " + map.visibility(pos));
      
      return map.visibility(pos);
   }
}
