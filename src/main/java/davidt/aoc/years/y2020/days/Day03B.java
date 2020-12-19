package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day03B
{
   private static final URL INPUT_FILE_LOC = Day03B.class.getResource("input/input3.txt");
   
   private static final char TREE = '#';
   
   private static final List <Position> SLOPES = List.of(
      new Position(new int[] {1, 1}),
      new Position(new int[] {3, 1}),
      new Position(new int[] {5, 1}),
      new Position(new int[] {7, 1}),
      new Position(new int[] {1, 2})
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
      
      for (Position slope : SLOPES)
      {
         Position currentPos = new Position(2);
         
         int trees = 0;
         
         while (currentPos.getY() < hillMap.getBoundSize().getY())
         {
            if (hillMap.get(currentPos) == TREE)
               trees++;
            
            currentPos.addBy(slope);
            currentPos.setX(currentPos.getX() % hillMap.getBoundSize().getX());
         }
         
         treeMultiple *= trees;
      }
      
      return treeMultiple;
   }
}
