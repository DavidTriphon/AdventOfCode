package days;

import security.*;
import util.*;

import java.io.*;


public class Day4
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input4.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(countValids(INPUT_FILE_LOC));
   }
   
   
   public static int countValids(String file) throws IOException
   {
      int validPassportCount = 0;
      
      for (Passport passport : Passport.getPassportsFromInput(file))
      {
         if (passport.hasRequiredFields())
            validPassportCount++;
      }
      
      return validPassportCount;
   }
}
