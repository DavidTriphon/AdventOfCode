package davidt.aoc.years.y2017.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;


public class Day01
{
   public static final URL INPUT_FILE_LOC = Day01.class.getResource("input/input1.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      String inputString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      System.out.println("A : " + countRepeatsSum(inputString));
      System.out.println("B : " + countOppositesSum(inputString));
   }
   
   
   public static int countRepeatsSum(String line)
   {
      int sum = 0;
      
      char previousChar = line.charAt(line.length() - 1);
      
      for (char letter : line.toCharArray())
      {
         if (letter == previousChar)
            sum += Character.digit(letter, 10);
         else
            previousChar = letter;
      }
      
      return sum;
   }
   
   
   public static int countOppositesSum(String line)
   {
      int sum = 0;
      
      for (int i = 0; i < line.length() / 2; i++)
      {
         if (line.charAt(i) == line.charAt(i + (line.length() / 2)))
            sum += Character.digit(line.charAt(i), 10) * 2;
      }
      
      return sum;
   }
}
