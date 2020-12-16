package davidt.aoc.years.y2020.days;

import davidt.aoc.years.y2020.security.*;

import java.io.*;
import java.net.*;


public class Day04B
{
   public static final URL INPUT_FILE_LOC = Day04B.class.getResource("input/input4.txt");
   
   
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
