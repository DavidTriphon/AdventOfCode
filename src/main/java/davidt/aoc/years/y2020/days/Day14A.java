package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day14A
{
   public static final URL INPUT_FILE_LOC = Day14A.class.getResource("input/input14.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      long andMask = Long.MAX_VALUE;
      long orMask = 0;
      
      HashMap <Long, Long> memory = new HashMap <>();
      
      for (String line : ReaderUtil.parseFileToList(INPUT_FILE_LOC, (line) -> line))
      {
         switch (line.substring(0, 3))
         {
            case "mas":
            {
               String mask = line.split(" ")[2];
               andMask = Long.parseLong(mask.replace('X', '1'), 2);
               orMask  = Long.parseLong(mask.replace('X', '0'), 2);
            }
            break;
            case "mem":
            {
               long value = Long.parseLong(line.split(" ")[2]);
               long address = Long.parseLong(line.split("\\[")[1].split("]")[0]);
               
               value &= andMask;
               value |= orMask;
               
               memory.put(address, value);
            }
            break;
         }
      }
      
      return memory.values().stream().mapToLong(l -> l).sum();
   }
}
