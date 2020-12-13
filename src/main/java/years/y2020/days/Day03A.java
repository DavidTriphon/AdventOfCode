package years.y2020.days;

import util.*;

import java.io.*;
import java.util.concurrent.atomic.*;


public class Day03A
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2020/input3.txt";
   
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
