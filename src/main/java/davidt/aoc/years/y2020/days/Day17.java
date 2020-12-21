package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;


public class Day17
{
   public static final URL INPUT_FILE_LOC = Day17.class.getResource("input/input17.txt");
   
   public static final char ALIVE = '#';
   public static final char DEAD  = '.';
   
   public static final int BOOT_UP_CYCLES = 6;
   
   
   public static void main(String[] args) throws IOException
   {
      String mapString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      System.out.println("A : " + part1(mapString));
      System.out.println("B : " + part2(mapString));
   }
   
   
   public static int part1(String mapString)
   {
      return countBootUp(mapString, 3);
   }
   
   
   public static int part2(String mapString)
   {
      return countBootUp(mapString, 4);
   }
   
   
   public static int countBootUp(String mapString, int dims)
   {
      InfiniteGridMap <Character> gridMap = new InfiniteGridMap <>(dims, DEAD);
      GridMap.fillFromString(gridMap, mapString, (c) -> c);
      
      for (int i = 0; i < BOOT_UP_CYCLES; i++)
      {
         gridMap.applyRule(Day17::conwayRuleset);
      }
      
      return gridMap.countOf(ALIVE);
   }
   
   
   public static Character conwayRuleset(Position pos, GridMap <Character, ?> map)
   {
      int aliveNeighborCount = map.countNeighborsOf(pos).getOrDefault(ALIVE, 0);
      
      boolean willBeAlive = (map.get(pos) == ALIVE) ?
         (aliveNeighborCount == 2 || aliveNeighborCount == 3) :
         (aliveNeighborCount == 3);
      
      return willBeAlive ? ALIVE : DEAD;
   }
}
