package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.xmas.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day09A
{
   public static final URL INPUT_FILE_LOC = Day09A.class.getResource("input/input9.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      List <Long> numbers = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Long::parseLong);
   
      return XMAS.findNonSumNumber(numbers, 25);
   }
}
