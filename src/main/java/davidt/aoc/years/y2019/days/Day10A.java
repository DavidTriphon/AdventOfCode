package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.asteroids.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day10A
{
   private static final URL INPUT_FILE_LOC = Day10A.class.getResource("input/input10.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      AsteroidMap map = AsteroidMap.fromString(fileString);
      
      Map.Entry <Integer, Integer> pos = map.mostVisibleAsteroid();
      
      System.out.printf("(%d,%d)\n", pos.getKey(), pos.getValue());
      System.out.println("Visibility: " + map.visibility(pos));
      
      return map.visibility(pos);
   }
}
