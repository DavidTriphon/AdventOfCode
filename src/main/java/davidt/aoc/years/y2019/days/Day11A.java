package davidt.aoc.years.y2019.days;

import davidt.aoc.map.*;
import davidt.aoc.years.y2019.cpu.*;
import davidt.aoc.years.y2019.paint.*;

import java.io.*;
import java.net.*;


public class Day11A
{
   private static final URL INPUT_FILE_LOC = Day11A.class.getResource("input/input11.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
      
      Program program = new Program();
      program.setMemory(opCodes);
      
      InfiniteGridMap <Integer> map = new InfiniteGridMap <>(2, EmergencyPaintingRobot.PAINT_OLD);
      
      EmergencyPaintingRobot robot = new EmergencyPaintingRobot(program, map);
      
      robot.run();
      
      long black = map.countOf(EmergencyPaintingRobot.PAINT_BLACK);
      long white = map.countOf(EmergencyPaintingRobot.PAINT_WHITE);
      
      return black + white;
   }
}
