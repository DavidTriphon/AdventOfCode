package days;

import security.*;
import util.*;

import java.io.*;


public class Day4B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input4.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      int validPassports = countValids(INPUT_FILE_LOC);
      System.out.println("Valid Passports: " + validPassports);
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
