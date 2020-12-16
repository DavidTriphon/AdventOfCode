package davidt.aoc.years.y2020.seating;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;
import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;


class SeatAutomataTest
{
   public static final URL INPUT_FILE_LOC = SeatAutomataTest.class.getResource("smallRoom1.txt");
   
   
   @Test
   public void testSmallRoom() throws IOException
   {
      FiniteGridMap <SeatAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatAutomata::getStateFromLetter);
      
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
      
      assertEquals(37, seatLayout.countOf(SeatAutomata.TAKEN_SEAT));
   }
}
