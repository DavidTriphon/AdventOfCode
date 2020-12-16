package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;


public class Day03B
{
   private static final URL INPUT_FILE_LOC = Day03B.class.getResource("input/input3.txt");
   
   private static final char TREE = '#';
   
   private static final List <Point> SLOPES = List.of(
      new Point(1, 1),
      new Point(3, 1),
      new Point(5, 1),
      new Point(7, 1),
      new Point(1, 2)
   );
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      FiniteGridMap <Character> hillMap =
         FiniteGridMap.fromString(ReaderUtil.getFileString(INPUT_FILE_LOC), c -> c);
      
      long treeMultiple = 1;
      
      for (Point slope : SLOPES)
      {
         Point currentPos = new Point(0, 0);
         
         int trees = 0;
         
         while (currentPos.y < hillMap.height)
         {
            if (hillMap.get(currentPos) == TREE)
               trees++;
            
            currentPos.translate(slope.x, slope.y);
            currentPos.x = currentPos.x % hillMap.width;
         }
         
         treeMultiple *= trees;
      }
      
      return treeMultiple;
   }
}
