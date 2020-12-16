package davidt.aoc.years.y2020.days;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;
import davidt.aoc.util.*;
import davidt.aoc.years.y2020.seating.*;

import java.io.*;
import java.net.*;


public class Day11A
{
   public static final URL INPUT_FILE_LOC = Day11A.class.getResource("input/input11.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      FiniteGridMap <SeatAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatAutomata::getStateFromLetter);
   
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
   
      //System.out.println();
      //System.out.println(seatLayout.toMapString(SeatAutomata::getVisualLetter));
      //System.out.println();
   
      return seatLayout.countOf(SeatAutomata.TAKEN_SEAT);
   }
}
