package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day12A
{
   public static final URL INPUT_FILE_LOC = Day12A.class.getResource("input/input12.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <Map.Entry <Character, Integer>> instructions =
         ReaderUtil.parseFileToList(
            INPUT_FILE_LOC,
            (string) -> Map.entry(string.charAt(0), Integer.parseInt(string.substring(1)))
         );
   
      Position pos = new Position(2);
   
      CompassDir dir = CompassDir.RIGHT;
   
      for (Map.Entry <Character, Integer> instruction : instructions)
      {
         switch (instruction.getKey())
         {
            case 'N':
               pos.addBy(CompassDir.UP, instruction.getValue());
               break;
            case 'S':
               pos.addBy(CompassDir.DOWN, instruction.getValue());
               break;
            case 'E':
               pos.addBy(CompassDir.RIGHT, instruction.getValue());
               break;
            case 'W':
               pos.addBy(CompassDir.LEFT, instruction.getValue());
               break;
            case 'L':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  dir = dir.left();
               }
               break;
            case 'R':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  dir = dir.right();
               }
               break;
            case 'F':
               pos.addBy(dir, instruction.getValue());
               break;
         }
      }
   
      return pos.calcTaxicabDistance();
   }
}
