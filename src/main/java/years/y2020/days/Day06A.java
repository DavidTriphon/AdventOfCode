package years.y2020.days;

import util.*;

import java.io.*;
import java.util.*;


public class Day06A
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input6.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <String> groups = ReaderUtil.parseFileToList(
         INPUT_FILE_LOC, "\n\n", (group) ->
         {
            StringBuilder builder = new StringBuilder(group.trim());
            
            int findIndex;
            while ((findIndex = builder.indexOf("\n")) != -1)
            {
               builder.replace(findIndex, findIndex + "\n".length(), "");
            }
            
            for (int index = 0; index < builder.length(); index++)
            {
               char letter = builder.charAt(index);
               
               while ((findIndex = builder.lastIndexOf("" + letter)) != index)
               {
                  builder.replace(findIndex, findIndex + 1, "");
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
