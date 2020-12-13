package years.y2020.security;

import org.junit.jupiter.api.*;
import util.*;
import years.y2020.days.*;

import java.io.*;
import java.util.*;


class PassportTest
{
   public static final String INVALID_LOC =
      ReaderUtil.TEST_RESOURCES_LOCATION + "y2020/security/invalid.txt";
   public static final String VALID_LOC   =
      ReaderUtil.TEST_RESOURCES_LOCATION + "y2020/security/valid.txt";
   
   
   @Test
   void checkInvalidPassports() throws IOException
   {
      List <Passport> invalidPassports = Passport.getPassportsFromInput(INVALID_LOC);
      
      Assertions.assertEquals(invalidPassports.size(), 4,
         "There are not a correct number of parsed invalid passports!");
      
      for (Passport passport : invalidPassports)
      {
         Assertions.assertFalse(passport.hasValidValues(), String.format(
            "The following passport was considered valid but it is supposed to be invalid:\n%s",
            passport.toString()));
      }
   }
   
   
   @Test
   void checkValidPassports() throws IOException
   {
      List <Passport> validPassports = Passport.getPassportsFromInput(VALID_LOC);
   
      Assertions.assertEquals(validPassports.size(), 4,
         "There are not a correct number of parsed valid passports!");
      
      for (Passport passport : validPassports)
      {
         Assertions.assertTrue(passport.hasValidValues(), String.format(
            "The following passport was considered invalid but it is supposed to be valid:\n%s",
            passport.toString()));
      }
   }
   
   
   @Test
   void checkDay4() throws IOException
   {
      Assertions.assertEquals(Day4.countValids(Day4.INPUT_FILE_LOC), 260);
   }
   
   @Test
   void checkDay4B() throws IOException
   {
      Assertions.assertEquals(Day4B.countValids(Day4B.INPUT_FILE_LOC), 153);
   }
}