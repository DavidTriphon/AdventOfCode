package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.nano.*;

import java.io.*;
import java.net.*;


public class Day14A
{
   private static final URL INPUT_FILE_LOC = Day14A.class.getResource("input/input14.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      NanoFactory fact = new NanoFactory(
         ReaderUtil.parseFileToList(INPUT_FILE_LOC, Recipe::fromString));
      
      return fact.getOreCostOfFuel();
   }
}
