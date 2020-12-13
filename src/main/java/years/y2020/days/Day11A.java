package years.y2020.days;

import map.*;
import seating.*;
import util.*;

import java.io.*;


public class Day11A
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input11.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      FiniteGridMap <SeatAutomata> seatLayout = FiniteGridMap.fromString(
         ReaderUtil.getFileString(INPUT_FILE_LOC), SeatAutomata::getStateFromLetter);
      
      seatLayout.applyRuleUntilStable(SeatAutomata::nextState, 1);
      
      //System.out.println();
      //System.out.println(seatLayout.toMapString(SeatAutomata::getVisualLetter));
      //System.out.println();
      
      return seatLayout.count().get(SeatAutomata.TAKEN_SEAT);
   }
}
