package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.*;


public class Day03A
{
   private static final URL INPUT_FILE_LOC = Day03A.class.getResource("input/input3.txt");
   
   private static final int  SLOPE = 3;
   private static final char TREE  = '#';
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      AtomicInteger currentX = new AtomicInteger();
      AtomicInteger trees = new AtomicInteger();
      
      ReaderUtil.parseFileWithMethod(
         INPUT_FILE_LOC,
         (String line) ->
         {
            if (line.charAt(currentX.get() % line.length()) == TREE)
            {
               trees.getAndIncrement();
            }
            
            currentX.addAndGet(SLOPE);
         }
      );
      
      return trees.get();
   }
}
