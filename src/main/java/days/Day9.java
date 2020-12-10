package days;

import util.*;
import xmas.*;

import java.io.*;
import java.util.*;


public class Day9
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input9.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      List <Long> numbers = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Long::parseLong);
      
      System.out.println(XMAS.findNonSumNumber(numbers, 25));
   }
}
