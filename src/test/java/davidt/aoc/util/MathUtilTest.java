package davidt.aoc.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class MathUtilTest
{
   
   @Test
   void gcd()
   {
      assertEquals(1, MathUtil.gcd(2, 5));
      assertEquals(0, MathUtil.gcd(0, 0));
      assertEquals(1, MathUtil.gcd(0, 1));
      assertEquals(1, MathUtil.gcd(1, 0));
      assertEquals(1, MathUtil.gcd(1, 1));
      assertEquals(2, MathUtil.gcd(0, 2));
      assertEquals(2, MathUtil.gcd(2, 0));
      assertEquals(1, MathUtil.gcd(2, 1));
   }
   
   
   @Test
   void lcm()
   {
      assertEquals(10, MathUtil.lcm(2, 5));
      assertEquals(10, MathUtil.lcm(new long[] {2, 5}));
      
      assertEquals(0, MathUtil.lcm(0, 1));
      assertEquals(1, MathUtil.lcm(1, 1));
      assertEquals(10, MathUtil.lcm(10, 1));
      assertEquals(10, MathUtil.lcm(10, 2));
      assertEquals(30, MathUtil.lcm(10, 3));
   }
   
   
   @Test
   void binomial()
   {
      assertEquals(1, MathUtil.binomial(0, 0));
      
      assertEquals(1, MathUtil.binomial(1, 0));
      assertEquals(1, MathUtil.binomial(1, 1));
      
      assertEquals(1, MathUtil.binomial(2, 0));
      assertEquals(2, MathUtil.binomial(2, 1));
      assertEquals(1, MathUtil.binomial(2, 2));
      
      assertEquals(1, MathUtil.binomial(3, 0));
      assertEquals(3, MathUtil.binomial(3, 1));
      assertEquals(3, MathUtil.binomial(3, 2));
      assertEquals(1, MathUtil.binomial(3, 3));
      
      assertEquals(1, MathUtil.binomial(4, 0));
      assertEquals(4, MathUtil.binomial(4, 1));
      assertEquals(6, MathUtil.binomial(4, 2));
      assertEquals(4, MathUtil.binomial(4, 3));
      assertEquals(1, MathUtil.binomial(4, 4));
      
      assertEquals(126410606437752L, MathUtil.binomial(50, 25));
      
      System.out.println(MathUtil.binomial(100, 50));
   }
   
   
   @Test
   void factorial()
   {
      assertEquals(1, MathUtil.factorial(0));
      assertEquals(1, MathUtil.factorial(1));
      assertEquals(2, MathUtil.factorial(2));
      assertEquals(6, MathUtil.factorial(3));
      assertEquals(24, MathUtil.factorial(4));
      
      assertEquals(2432902008176640000L, MathUtil.factorial(20));
   }
}
