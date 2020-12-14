package years.y2019.days;

import map.*;
import util.*;
import years.y2019.cpu.*;
import years.y2019.paint.*;

import java.awt.*;
import java.io.*;


public class Day11B
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input11.txt";
   
   
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
