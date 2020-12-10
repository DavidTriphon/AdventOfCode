package days;

import jolt.*;
import util.*;

import java.io.*;
import java.util.*;


public class Day10B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input10.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      List <Integer> adapterRatings = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Integer::parseInt);
      
      System.out.println(AdapterChain.countAdapterCombinations(adapterRatings));
   }
}
