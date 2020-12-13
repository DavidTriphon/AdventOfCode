package main.years.y2020.days;

import main.util.*;
import main.years.y2020.jolt.*;

import java.io.*;
import java.util.*;


public class Day10B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input10.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   public static long getAnswer() throws IOException
   {
      List <Integer> adapterRatings = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Integer::parseInt);
   
      return AdapterChain.countAdapterCombinations(adapterRatings);
   }
}
