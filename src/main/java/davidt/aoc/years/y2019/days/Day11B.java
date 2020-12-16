package davidt.aoc.years.y2019.days;

import davidt.aoc.map.*;
import davidt.aoc.years.y2019.cpu.*;
import davidt.aoc.years.y2019.paint.*;

import java.awt.*;
import java.io.*;
import java.net.*;


public class Day11B
{
   private static final URL INPUT_FILE_LOC = Day11B.class.getResource("input/input11.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static String getAnswer() throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
      
      Program program = new Program();
      program.setMemory(opCodes);
      
      InfiniteGridMap <Integer> map = new InfiniteGridMap <>(EmergencyPaintingRobot.PAINT_OLD);
      map.set(new Point(), EmergencyPaintingRobot.PAINT_WHITE);
      
      EmergencyPaintingRobot robot = new EmergencyPaintingRobot(program, map);
      
      robot.run();
      
      return map.toMapString(
         (i) -> {
            switch (i)
            {
               case EmergencyPaintingRobot.PAINT_BLACK:
               case EmergencyPaintingRobot.PAINT_OLD:
               {
                  return ' ';
               }
               case EmergencyPaintingRobot.PAINT_WHITE:
               {
                  return 'X';
               }
               default:
                  return '.';
            }
         }
      );
   }
}
