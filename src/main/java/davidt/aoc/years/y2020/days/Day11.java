package davidt.aoc.years.y2020.days;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;
import davidt.aoc.util.*;
import davidt.aoc.years.y2020.seating.*;

import java.io.*;
import java.net.*;


public class Day11
{
   public static final URL INPUT_FILE_LOC = Day11.class.getResource("input/input11.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println("A : " + part1(parseInput1(INPUT_FILE_LOC)));
      System.out.println("B : " + part2(parseInput2(INPUT_FILE_LOC)));
   }
   
   
   public static FiniteGridMap <SeatAutomata> parseInput1(URL inputURL) throws IOException
   {
      return FiniteGridMap
         .fromString(ReaderUtil.getFileString(inputURL), SeatAutomata::getStateFromLetter);
   }
   
   
   public static int part1(FiniteGridMap <SeatAutomata> seatLayout)
   {
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
      return seatLayout.countOf(SeatAutomata.TAKEN_SEAT);
   }
   
   
   public static FiniteGridMap <SeatSightAutomata> parseInput2(URL inputURL) throws IOException
   {
      return FiniteGridMap
         .fromString(ReaderUtil.getFileString(inputURL), SeatSightAutomata::getStateFromLetter);
   }
   
   
   public static int part2(FiniteGridMap <SeatSightAutomata> seatLayout)
   {
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
      return seatLayout.countOf(SeatSightAutomata.TAKEN_SEAT);
   }
}
