package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.jolt.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day10A
{
   public static final URL INPUT_FILE_LOC = Day10A.class.getResource("input/input10.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <Integer> adapterRatings = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Integer::parseInt);
      
      return AdapterChain.findAdapterRating(adapterRatings);
   }
}
