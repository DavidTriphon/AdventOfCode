package davidt.aoc.map;

import org.junit.jupiter.api.*;

import java.util.*;

import static davidt.aoc.map.Polarity.*;
import static org.junit.jupiter.api.Assertions.*;


class PolarityTest
{
   
   @Test
   void getNumericNeutral()
   {
      List <Polarity> polarities = new ArrayList <>();
      assertEquals(0, Polarity.getNumeric(polarities.toArray(new Polarity[0])));
      polarities.add(NEUTRAL);
      assertEquals(0, Polarity.getNumeric(polarities.toArray(new Polarity[0])));
      polarities.add(NEUTRAL);
      assertEquals(0, Polarity.getNumeric(polarities.toArray(new Polarity[0])));
      polarities.add(NEUTRAL);
      assertEquals(0, Polarity.getNumeric(polarities.toArray(new Polarity[0])));
   }
   
   
   @Test
   void getNumericOneDimensional()
   {
      assertEquals(1, Polarity.getNumeric(POSITIVE));
      assertEquals(0, Polarity.getNumeric(NEUTRAL));
      assertEquals(-1, Polarity.getNumeric(NEGATIVE));
   }
   
   
   @Test
   void getNumericTwoDimensional()
   {
      assertEquals(0, Polarity.getNumeric(NEUTRAL, NEUTRAL));
      assertEquals(1, Polarity.getNumeric(POSITIVE, NEUTRAL));
      assertEquals(2, Polarity.getNumeric(NEGATIVE, POSITIVE));
      assertEquals(3, Polarity.getNumeric(NEUTRAL, POSITIVE));
      assertEquals(4, Polarity.getNumeric(POSITIVE, POSITIVE));
      assertEquals(-4, Polarity.getNumeric(NEGATIVE, NEGATIVE));
      assertEquals(-3, Polarity.getNumeric(NEUTRAL, NEGATIVE));
      assertEquals(-2, Polarity.getNumeric(POSITIVE, NEGATIVE));
      assertEquals(-1, Polarity.getNumeric(NEGATIVE, NEUTRAL));
   }
}
