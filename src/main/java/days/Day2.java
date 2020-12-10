package days;

import util.*;

import java.io.*;
import java.util.concurrent.atomic.*;


public class Day2
{
   private static final String INPUT_FILE_LOC = "input2.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      AtomicInteger validPasswordCount = new AtomicInteger();
      
      ReaderUtil.parseFileWithMethod(
         INPUT_FILE_LOC,
         (String line) ->
         {
            String[] parts = line.split(":");
            char requirement = parts[0].charAt(parts[0].length()-1);
            String[] bounds = parts[0].substring(0, parts[0].length()-2).split("-");
            String password = parts[1].substring(1);
            int min = Integer.parseInt(bounds[0]);
            int max = Integer.parseInt(bounds[1]);
            
            int reqCount = 0;
            
            for (char c: password.toCharArray())
            {
               if (c == requirement)
                  reqCount++;
            }
            
            if (min <= reqCount && reqCount <= max)
               validPasswordCount.getAndIncrement();
         }
      );
      
      System.out.println(validPasswordCount.get());
   }
}
