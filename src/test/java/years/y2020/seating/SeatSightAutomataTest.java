package years.y2020.seating;

import map.*;
import org.junit.jupiter.api.*;
import util.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class SeatSightAutomataTest
{
   public static final String INPUT_FILE_LOC =
      ReaderUtil.TEST_RESOURCES_LOCATION + "years/y2020/seating/smallRoom1.txt";
   
   @Test
   public void testSmallRoom() throws IOException
   {
      FiniteGridMap <SeatSightAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatSightAutomata::getStateFromLetter);
   
      seatLayout.applyRuleUntilStable(SeatSightAutomata::nextState, 1);
   
      assertEquals(26, seatLayout.count().get(SeatSightAutomata.TAKEN_SEAT));
   }
}
