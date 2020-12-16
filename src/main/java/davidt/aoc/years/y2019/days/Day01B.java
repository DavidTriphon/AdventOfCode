package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;


public class Day01B
{
   private static final URL INPUT_FILE_LOC = Day01B.class.getResource("input/input1.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      long totalFuel = 0;
   
      for (int fuelPartial : ReaderUtil.parseFileToList(INPUT_FILE_LOC, Integer::parseInt))
      {
         while ((fuelPartial = (fuelPartial / 3) - 2) > 0)
         {
            totalFuel += fuelPartial;
         }
      }
   
      return totalFuel;
   }
}
