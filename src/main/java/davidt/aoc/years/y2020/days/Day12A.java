package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;
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
      
      Point pos = new Point();
      
      Direction dir = Direction.RIGHT;
      
      for (Map.Entry <Character, Integer> instruction : instructions)
      {
         switch (instruction.getKey())
         {
            case 'N':
               pos.y += instruction.getValue();
               break;
            case 'S':
               pos.y -= instruction.getValue();
               break;
            case 'E':
               pos.x += instruction.getValue();
               break;
            case 'W':
               pos.x -= instruction.getValue();
               break;
            case 'L':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  dir = dir.ccw90();
               }
               break;
            case 'R':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  dir = dir.cw90();
               }
               break;
            case 'F':
               dir.move(pos, instruction.getValue());
               break;
         }
      }
      
      return Math.abs(pos.x) + Math.abs(pos.y);
   }
}
