package test.years.y2020.seating;

import main.map.*;
import main.util.*;
import main.years.y2020.seating.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class SeatAutomataTest
{
   public static final String INPUT_FILE_LOC =
      ReaderUtil.TEST_RESOURCES_LOCATION + "years/y2020/seating/smallRoom1.txt";
   
   @Test
   public void testSmallRoom() throws IOException
   {
      FiniteGridMap <SeatAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatAutomata::getStateFromLetter);
   
      seatLayout.applyRuleUntilStable(SeatAutomata::nextState, 1);
   
      assertEquals(37, seatLayout.count().get(SeatAutomata.TAKEN_SEAT));
   }
}
