package seating;

import map.*;
import org.junit.jupiter.api.*;
import util.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class SeatAutomataTest
{
   public static final String INPUT_FILE_LOC =
      ReaderUtil.TEST_RESOURCES_LOCATION + "seating/smallRoom1.txt";
   
   @Test
   public void testSmallRoom() throws IOException
   {
      FiniteGridMap <SeatAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatAutomata::getStateFromLetter);
   
      seatLayout.applyRuleUntilStable(SeatAutomata::nextState, 1);
   
      assertEquals(37, seatLayout.count().get(SeatAutomata.TAKEN_SEAT));
   }
}