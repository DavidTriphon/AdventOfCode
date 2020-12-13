package years.y2020.days;

import map.*;
import years.y2020.seating.*;
import util.*;

import java.io.*;


public class Day11B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "y2020/input11.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      FiniteGridMap <SeatSightAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatSightAutomata::getStateFromLetter);
      
      seatLayout.applyRuleUntilStable(SeatSightAutomata::nextState, 1);
      
      System.out.println();
      System.out.println(seatLayout.toMapString(SeatSightAutomata::getVisualLetter));
      System.out.println();
      
      return seatLayout.count().get(SeatSightAutomata.TAKEN_SEAT);
   }
}
