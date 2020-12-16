package davidt.aoc.years.y2020.security;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;


class PassportTest
{
   public static final URL INVALID_LOC = PassportTest.class.getResource("invalid.txt");
   public static final URL VALID_LOC   = PassportTest.class.getResource("valid.txt");
   
   
   @Test
   void checkInvalidPassports() throws IOException
   {
      List <Passport> invalidPassports = Passport.getPassportsFromInput(INVALID_LOC);
      
      Assertions.assertEquals(invalidPassports.size(), 4,
         "There are not a correct number of parsed invalid passports!"
      );
      
      for (Passport passport : invalidPassports)
      {
         Assertions.assertFalse(passport.hasValidValues(), String.format(
            "The following passport was considered valid but it is supposed to be invalid:\n%s",
            passport.toString()
         ));
      }
   }
   
   
   @Test
   void checkValidPassports() throws IOException
   {
      List <Passport> validPassports = Passport.getPassportsFromInput(VALID_LOC);
   
      Assertions.assertEquals(validPassports.size(), 4,
         "There are not a correct number of parsed valid passports!"
      );
      
      for (Passport passport : validPassports)
      {
         Assertions.assertTrue(passport.hasValidValues(), String.format(
            "The following passport was considered invalid but it is supposed to be valid:\n%s",
            passport.toString()
         ));
      }
   }
}
