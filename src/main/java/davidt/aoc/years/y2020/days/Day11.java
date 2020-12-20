package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day11
{
   public static final URL INPUT_FILE_LOC = Day11.class.getResource("input/input11.txt");
   
   public static final char TAKEN_SEAT = '#';
   public static final char EMPTY_SEAT = 'L';
   public static final char NO_SEAT    = '.';
   
   public static final DirectionSet NEIGHBORS = new DirectionSet(2, false, true);
   
   public static final Grid2DFlatPrinter <Character> PRINTER =
      new Grid2DFlatPrinter <>(Day11::printerMap, false);
   
   
   public static void main(String[] args) throws IOException
   {
      FiniteGridMap <Character> seatLayout1 = parseInput(INPUT_FILE_LOC);
      FiniteGridMap <Character> seatLayout2 = seatLayout1.copy();
      
      System.out.println("Input : ");
      System.out.println(PRINTER.toMapString(seatLayout1));
      
      System.out.println("A : " + part1(seatLayout1));
      System.out.println(PRINTER.toMapString(seatLayout1));
      
      System.out.println("B : " + part2(seatLayout2));
      System.out.println(PRINTER.toMapString(seatLayout2));
   }
   
   
   public static FiniteGridMap <Character> parseInput(URL inputURL) throws IOException
   {
      return FiniteGridMap.fromString(ReaderUtil.getFileString(inputURL), (c) -> c);
   }
   
   
   public static int part1(FiniteGridMap <Character> seatLayout)
   {
      seatLayout.applyRuleUntilStable(Day11::directNeighborRule, 1);
      return seatLayout.countOf(TAKEN_SEAT);
   }
   
   
   public static int part2(FiniteGridMap <Character> seatLayout)
   {
      seatLayout.applyRuleUntilStable(Day11::distantNeighborRule, 1);
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
   
   
   public static String printerMap(Character c)
   {
      switch (c)
      {
         case '#':
            return "X";
         case 'L':
            return "=";
      }
      return " ";
   }
}
