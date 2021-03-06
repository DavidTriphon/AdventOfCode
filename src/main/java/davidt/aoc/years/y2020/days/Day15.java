package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day15
{
   public static final URL INPUT_FILE_LOC = Day15.class.getResource("input/input15.txt");
   
   public static final int PART_ONE_COUNT = 2020;
   public static final int PART_TWO_COUNT = 30000000;
   
   
   public static void main(String[] args) throws IOException
   {
      List <Integer> input = getInputSequence(INPUT_FILE_LOC);
      System.out.println("A : " + continueSequence(input, PART_ONE_COUNT));
      System.out.println("B : " + continueSequence(input, PART_TWO_COUNT));
   }
   
   
   public static List <Integer> getInputSequence(URL inputURL) throws IOException
   {
      return ReaderUtil.parseFileToList(inputURL, ",", Integer::parseInt);
   }
   
   
   public static int continueSequence(List <Integer> startSequence, int count)
   {
      HashMap <Integer, Integer> lastSpokenTimes = new HashMap <>();
      
      int lastSpokenNumber = -1;
      int currentTime = 1; // because we count them as 1,2,3... and want the 'count'ed element
      
      for (int number : startSequence)
      {
         lastSpokenTimes.put(lastSpokenNumber, currentTime);
         lastSpokenNumber = number;
         currentTime++;
      }
      
      do
      {
         Integer lastSpokenTime = lastSpokenTimes.get(lastSpokenNumber);
         lastSpokenTimes.put(lastSpokenNumber, currentTime);
         lastSpokenNumber = ((lastSpokenTime == null) ? 0 : (currentTime - lastSpokenTime));
      }
      while (currentTime++ < count);
      
      return lastSpokenNumber;
   }
}
