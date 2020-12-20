package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.homework.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day18
{
   public static final URL INPUT_FILE_LOC = Day18.class.getResource("input/input18.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      List <String> equationStrings = ReaderUtil.parseFileToList(INPUT_FILE_LOC);
      
      System.out.println("A : " + part1(equationStrings));
      System.out.println("B : " + part2(equationStrings));
   }
   
   
   public static long part1(List <String> equationStrings)
   {
      long sum = 0;
      
      for (String equation : equationStrings)
      {
         sum += WeirdMath.compute(equation);
      }
      
      return sum;
   }
   
   
   public static long part2(List <String> equationStrings)
   {
      long sum = 0;
      
      for (String equation : equationStrings)
      {
         sum += BackwardsMath.compute(equation);
      }
      
      return sum;
   }
}
