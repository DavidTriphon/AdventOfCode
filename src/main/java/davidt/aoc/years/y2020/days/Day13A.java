package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;


public class Day13A
{
   public static final URL INPUT_FILE_LOC = Day13A.class.getResource("input/input13.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <String> lines = ReaderUtil.parseFileToList(INPUT_FILE_LOC);
      
      int startTime = Integer.parseInt(lines.get(0));
      
      List <Integer> delays = Arrays.stream(lines.get(1).split(",")).filter(
         (s) -> !s.equals("x")).map(Integer::parseInt).collect(
         Collectors.toUnmodifiableList());
      
      int earliestTime = Integer.MAX_VALUE;
      
      int bestWaitTime = Integer.MAX_VALUE;
      int bestBusID = 0;
      
      for (Integer delay : delays)
      {
         int waitTime = delay - (startTime % delay);
         
         if (startTime + waitTime < earliestTime)
         {
            earliestTime = startTime + waitTime;
            bestWaitTime = waitTime;
            bestBusID    = delay;
         }
      }
      
      return bestWaitTime * bestBusID;
   }
}
