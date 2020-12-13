package years.y2020.seating;

import automata.*;
import map.*;
import org.junit.jupiter.api.*;
import util.*;

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
      
      seatLayout.applyRuleUntilStable(IAutoState::autoRule, 1);
      
      assertEquals(37, seatLayout.count().get(SeatAutomata.TAKEN_SEAT));
   }
}
