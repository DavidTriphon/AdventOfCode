package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.nbody.*;

import java.io.*;
import java.net.*;


public class Day12B
{
   private static final URL INPUT_FILE_LOC = Day12B.class.getResource("input/input12.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      NBody nBody = NBody.fromString(fileString);
      
      return nBody.getLoopTime();
   }
}
