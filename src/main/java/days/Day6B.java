package days;

import util.*;

import java.io.*;
import java.util.*;


public class Day6B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input6.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(countGroupAnswers(INPUT_FILE_LOC));
   }
   
   
   public static int countGroupAnswers(String file) throws IOException
   {
      List <String> groups = ReaderUtil.parseFileToList(
         file, "\n\n", (group) ->
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
