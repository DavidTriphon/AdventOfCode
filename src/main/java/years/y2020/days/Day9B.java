package years.y2020.days;

import util.*;
import years.y2020.xmas.*;

import java.io.*;
import java.util.*;


public class Day9B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "2020/input9.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      List <Long> numbers = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Long::parseLong);
      
      long nonSumNumber = XMAS.findNonSumNumber(numbers, 25);
      
      long weaknessSum = XMAS.findWeaknessSum(numbers, nonSumNumber);
      
      System.out.println(weaknessSum);
   }
}
