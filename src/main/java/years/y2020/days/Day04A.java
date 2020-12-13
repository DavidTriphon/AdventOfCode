package main.years.y2020.days;

import main.util.*;
import main.years.y2020.security.*;

import java.io.*;


public class Day04A
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input4.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      int validPassportCount = 0;
      
      for (Passport passport : Passport.getPassportsFromInput(INPUT_FILE_LOC))
      {
         if (passport.hasRequiredFields())
            validPassportCount++;
      }
      
      return validPassportCount;
   }
}
