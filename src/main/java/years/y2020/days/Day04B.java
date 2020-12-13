package years.y2020.days;

import util.*;
import years.y2020.security.*;

import java.io.*;


public class Day04B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input4.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      int validPassports = getAnswer();
      System.out.println("Valid Passports: " + validPassports);
   }
   
   
   public static int getAnswer() throws IOException
   {
      int validPassportCount = 0;
      
      for (Passport passport : Passport.getPassportsFromInput(INPUT_FILE_LOC))
      {
         if (passport.hasValidValues())
            validPassportCount++;
      }
      
      return validPassportCount;
   }
}
