package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day14B
{
   public static final URL INPUT_FILE_LOC = Day14B.class.getResource("input/input14.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      List <Integer> floatIndexes = new ArrayList <>();
      long orMask = 0;
      long andMask = Long.MAX_VALUE;
      
      HashMap <Long, Long> memory = new HashMap <>();
      
      for (String line : ReaderUtil.parseFileToList(INPUT_FILE_LOC))
      {
         switch (line.substring(0, 3))
         {
            case "mas":
            {
               floatIndexes.clear();
               
               String maskString = line.split(" ")[2];
               
               orMask  = Long.parseLong(maskString.replace('X', '0'), 2);
               andMask = Long.parseLong(maskString.replace('0', '1').replace('X', '0'), 2);
               
               for (int i = 0; i < maskString.length(); i++)
               {
                  if (maskString.charAt(i) == 'X')
                     floatIndexes.add(0, maskString.length() - i - 1);
               }
            }
            break;
            case "mem":
            {
               long value = Long.parseLong(line.split(" ")[2]);
               
               long address = Long.parseLong(line.split("\\[")[1].split("]")[0]);
               address |= orMask;
               address &= andMask;
               
               for (int i = 0; i < (1 << floatIndexes.size()); i++)
               {
                  long floatAddress = 0;
                  
                  int iBits = i;
                  
                  for (int floatIndex : floatIndexes)
                  {
                     floatAddress |= (long) (iBits % 2) << floatIndex;
                     iBits /= 2;
                  }
                  
                  memory.put(address | floatAddress, value);
               }
            }
            break;
         }
      }
      
      return memory.values().stream().mapToLong(l -> l).sum();
   }
}
