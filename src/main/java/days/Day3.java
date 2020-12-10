package days;

import util.*;

import java.io.*;
import java.util.concurrent.atomic.*;


public class Day3
{
   private static final String INPUT_FILE_LOC = "input3.txt";
   
   private static final int SLOPE = 3;
   private static final char TREE = '#';
   
   public static void main(String[] args) throws IOException
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
   
      System.out.println(trees.get());
   }
}
