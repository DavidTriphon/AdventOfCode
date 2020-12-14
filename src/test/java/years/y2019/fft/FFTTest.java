package years.y2019.fft;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class FFTTest
{
   
   @Test
   void process()
   {
      // basic test
      int[] digits = FFT.getDigitsFromString("12345678");
      digits = FFT.process(digits);
      assertArrayEquals(FFT.getDigitsFromString("48226158"), digits);
      
      // listed examples
      processhelper("80871224585914546619083218645595", "24176176");
      processhelper("19617804207202209144916044189917", "73745418");
      processhelper("69317163492948606335995924319873", "52432133");
   }
   
   
   void processhelper(String source, String expectedEnd)
   {
      int[] digits = FFT.getDigitsFromString(source);
      for (int i = 0; i < 100; i++)
      {
         digits = FFT.process(digits);
      }
      assertArrayEquals(
         FFT.getDigitsFromString(expectedEnd), Arrays.stream(digits).limit(8).toArray(),
         String.format("%s should lead to %s after 100 phases.", source, expectedEnd)
      );
   }
   
   
   @Test
   void getDigitsFromString()
   {
      int[] digits = FFT.getDigitsFromString("1234567890");
      
      assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, digits);
   }
   
   
   @Test
   void stringifyDigits()
   {
      String string = FFT.stringifyDigits(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
      
      assertEquals("1234567890", string);
   }
}
