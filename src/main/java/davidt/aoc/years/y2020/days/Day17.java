package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day17
{
   public static final URL INPUT_FILE_LOC = Day17.class.getResource("input/input17.txt");
   
   public static final char ALIVE = '#';
   public static final char DEAD  = '.';
   
   public static final int BOOT_UP_CYCLES = 6;
   
   public static final DirectionSet NEIGHBORS_3D = new DirectionSet(3, false, true);
   public static final DirectionSet NEIGHBORS_4D = new DirectionSet(4, false, true);
   
   
   public static void main(String[] args) throws IOException
   {
      String mapString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      System.out.println("A : " + part1(mapString));
      System.out.println("B : " + part2(mapString));
   }
   
   
   public static int part1(String mapString)
   {
      InfiniteGridMap <Character> map3D = new InfiniteGridMap <>(3, DEAD);
      GridMap.fillFromString(map3D, mapString, (c) -> c);
      
      for (int i = 0; i < BOOT_UP_CYCLES; i++)
      {
         map3D.applyRule(Day17::cubeRuleset);
      }
      
      return map3D.countOf(ALIVE);
   }
   
   
   public static int part2(String mapString)
   {
      InfiniteGridMap <Character> map4D = new InfiniteGridMap <>(4, DEAD);
      GridMap.fillFromString(map4D, mapString, (c) -> c);
      
      for (int i = 0; i < BOOT_UP_CYCLES; i++)
      {
         map4D.applyRule(Day17::hypercubeRuleset);
      }
      
      return map4D.countOf(ALIVE);
   }
   
   
   public static Character cubeRuleset(Position pos, GridMap <Character, ?> map)
   {
      return nextState(map.get(pos), map.countNeighborsOf(pos, NEIGHBORS_3D));
   }
   
   
   public static Character hypercubeRuleset(Position pos, GridMap <Character, ?> map)
   {
      return nextState(map.get(pos), map.countNeighborsOf(pos, NEIGHBORS_4D));
   }
   
   
   public static char nextState(char currState, Map <Character, Integer> neighbors)
   {
      int aliveNeighborCount = neighbors.getOrDefault(ALIVE, 0);
      
      boolean isAlive = (currState == ALIVE) ?
         (aliveNeighborCount == 2 || aliveNeighborCount == 3) :
         (aliveNeighborCount == 3);
      
      return isAlive ? ALIVE : DEAD;
   }
}
