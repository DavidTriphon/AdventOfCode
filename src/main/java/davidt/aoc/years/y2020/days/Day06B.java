package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day06B
{
   public static final URL INPUT_FILE_LOC = Day06B.class.getResource("input/input6.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <String> groups = ReaderUtil.parseFileToList(
         INPUT_FILE_LOC, "\n\n", (group) ->
         {
            String[] individuals = group.split("\n");
            
            StringBuilder builder = new StringBuilder(individuals[0]);
            
            for (int i = 1; i < individuals.length; i++)
            {
               
               for (int j = 0; j < builder.length(); j++)
               {
                  if (individuals[i].indexOf(builder.charAt(j)) == -1)
                  {
                     builder.replace(j, j + 1, "");
                     j--;
                  }
               }
            }
            
            return builder.toString();
         }
      );
      
      int sum = 0;
      
      for (String group : groups)
      {
         sum += group.length();
      }
      
      return sum;
   }
}
