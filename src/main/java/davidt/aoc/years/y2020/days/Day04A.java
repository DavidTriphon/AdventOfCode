package davidt.aoc.years.y2020.days;

import davidt.aoc.years.y2020.security.*;

import java.io.*;
import java.net.*;


public class Day04A
{
   public static final URL INPUT_FILE_LOC = Day04A.class.getResource("input/input4.txt");
   
   
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
