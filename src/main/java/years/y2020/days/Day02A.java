package main.years.y2020.days;

import main.util.*;

import java.io.*;


public class Day02A
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2020/input2.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      return ReaderUtil.parseFileToList(
         INPUT_FILE_LOC,
         (String line) ->
         {
            String[] parts = line.split(":");
            char requirement = parts[0].charAt(parts[0].length() - 1);
            String[] bounds = parts[0].substring(0, parts[0].length() - 2).split("-");
            String password = parts[1].substring(1);
            int min = Integer.parseInt(bounds[0]);
            int max = Integer.parseInt(bounds[1]);
            
            int reqCount = 0;
            
            for (char c : password.toCharArray())
            {
               if (c == requirement)
                  reqCount++;
            }
            
            if (min <= reqCount && reqCount <= max)
               return 1;
            return 0;
         }
      ).stream().mapToInt(i -> i).sum();
   }
}
