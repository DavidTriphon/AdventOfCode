package davidt.aoc.years.y2019.days;

import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;


public class Day02A
{
   private static final URL INPUT_FILE_LOC = Day02A.class.getResource("input/input2.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      Integer[] opCodes = Computer.getMemoryFromFile(INPUT_FILE_LOC);
      
      Integer[] newMemory = Computer.compute(opCodes);
      
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < newMemory.length; i++)
      {
         sb.append(newMemory[i]);
         
         if (i + 1 < newMemory.length)
         {
            sb.append(',');
         }
      }
      
      System.out.println(sb.toString());
      
      return newMemory[0];
   }
}
