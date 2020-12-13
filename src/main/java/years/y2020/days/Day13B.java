package main.years.y2020.days;

import main.util.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Day13B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input13.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      List <Integer> delays = Arrays.stream(
         ReaderUtil.parseFileToList(INPUT_FILE_LOC, (s) -> s).get(1).split(",")).map(
         (s) -> s.equals("x") ? "-1" : s).map(Integer::parseInt).collect(
         Collectors.toUnmodifiableList());
      
      ArrayList <Map.Entry <Integer, Integer>> sortedDelays = new ArrayList <>();
      
      for (int i = 0; i < delays.size(); i++)
      {
         if (delays.get(i) != -1)
         {
            sortedDelays.add(Map.entry(delays.get(i), i));
         }
      }
      
      sortedDelays.sort(Comparator.comparingInt((entry) -> -entry.getKey()));
      
      long jumpPeriod = sortedDelays.get(0).getKey();
      long startTime = -sortedDelays.get(0).getValue();
      
      for (Map.Entry <Integer, Integer> delay : sortedDelays)
      {
         long divisorDelay = delay.getKey();
         long offset = delay.getValue();
         
         do
         {
            startTime += jumpPeriod;
         }
         while ((startTime + offset) % divisorDelay != 0);
         
         if (jumpPeriod % divisorDelay != 0)
            jumpPeriod = MathUtil.lcm(jumpPeriod, divisorDelay);
      }
      
      return startTime;
   }
}
