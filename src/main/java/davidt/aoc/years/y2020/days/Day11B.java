package davidt.aoc.years.y2020.days;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;
import davidt.aoc.util.*;
import davidt.aoc.years.y2020.seating.*;

import java.io.*;
import java.net.*;


public class Day11B
{
   public static final URL INPUT_FILE_LOC = Day12A.class.getResource("input/input11.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      FiniteGridMap <SeatSightAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatSightAutomata::getStateFromLetter);
   
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
   
      //System.out.println();
      //System.out.println(seatLayout.toMapString(SeatSightAutomata::getVisualLetter));
      //System.out.println();
   
      return seatLayout.countOf(SeatSightAutomata.TAKEN_SEAT);
   }
}
