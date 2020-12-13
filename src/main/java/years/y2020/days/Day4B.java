package years.y2020.days;

import util.*;
import years.y2020.security.*;

import java.io.*;


public class Day4B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "y2020/input4.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(countValids(INPUT_FILE_LOC));
   }
   
   
   public static int countValids(String file) throws IOException
   {
      int validPassportCount = 0;
      
      for (Passport passport : Passport.getPassportsFromInput(file))
      {
         if (passport.hasValidValues())
            validPassportCount++;
      }
      
      return validPassportCount;
   }
}
