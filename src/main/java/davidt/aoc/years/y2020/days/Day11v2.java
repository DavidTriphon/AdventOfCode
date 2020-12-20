package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day11v2
{
   
   public static final URL INPUT_FILE_LOC = Day11.class.getResource("input/input11.txt");
   
   public static final char TAKEN_SEAT = '#';
   public static final char EMPTY_SEAT = 'L';
   public static final char NO_SEAT    = '.';
   
   public static final DirectionSet NEIGHBORS = new DirectionSet(2, false, true);
   
   
   public static void main(String[] args) throws IOException
   {
      FiniteGridMap <Character> seatLayout = parseInput(INPUT_FILE_LOC);
      
      System.out.println("A : " + part1(seatLayout.copy()));
      System.out.println("B : " + part2(seatLayout.copy()));
   }
   
   
   public static FiniteGridMap <Character> parseInput(URL inputURL) throws IOException
   {
      return FiniteGridMap.fromString(ReaderUtil.getFileString(inputURL), (c) -> c);
   }
   
   
   public static int part1(FiniteGridMap <Character> seatLayout)
   {
      seatLayout.applyRuleUntilStable(Day11v2::directNeighborRule, 1);
      return seatLayout.countOf(TAKEN_SEAT);
   }
   
   
   public static int part2(FiniteGridMap <Character> seatLayout)
   {
      seatLayout.applyRuleUntilStable(Day11v2::distantNeighborRule, 1);
      return seatLayout.countOf(TAKEN_SEAT);
   }
   
   
   public static Character directNeighborRule(Position pos, GridMap <Character, ?> map)
   {
      Map <Character, Integer> neighbors = map.countNeighborsOf(pos, NEIGHBORS);
      
      switch (map.get(pos))
      {
         
         case EMPTY_SEAT:
         {
            if (neighbors.getOrDefault(TAKEN_SEAT, 0) == 0)
               return TAKEN_SEAT;
         }
         break;
         case TAKEN_SEAT:
         {
            if (neighbors.getOrDefault(TAKEN_SEAT, 0) >= 4)
               return EMPTY_SEAT;
         }
         break;
      }
      
      // default no change
      return map.get(pos);
   }
   
   
   public static Character distantNeighborRule(Position pos, GridMap <Character, ?> map)
   {
      Map <Character, Integer> neighbors =
         map.countSeenFrom(pos, NEIGHBORS, List.of(NO_SEAT), false);
      
      switch (map.get(pos))
      {
         
         case EMPTY_SEAT:
         {
            if (neighbors.getOrDefault(TAKEN_SEAT, 0) == 0)
               return TAKEN_SEAT;
         }
         break;
         case TAKEN_SEAT:
         {
            if (neighbors.getOrDefault(TAKEN_SEAT, 0) >= 5)
               return EMPTY_SEAT;
         }
         break;
      }
      
      // default no change
      return map.get(pos);
   }
}
