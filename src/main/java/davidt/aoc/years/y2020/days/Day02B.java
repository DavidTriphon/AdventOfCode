package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;


public class Day02B
{
   private static final URL INPUT_FILE_LOC = Day02B.class.getResource("input/input2.txt");
   
   
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
            int posA = Integer.parseInt(bounds[0]) - 1;
            int posB = Integer.parseInt(bounds[1]) - 1;
   
            if ((password.length() >= posA && password.charAt(posA) == requirement)
                != (password.length() >= posB && password.charAt(posB) == requirement))
               return 1;
            return 0;
         }
      ).stream().mapToInt(i -> i).sum();
   }
}
