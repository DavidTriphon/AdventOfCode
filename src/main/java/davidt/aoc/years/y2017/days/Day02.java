package davidt.aoc.years.y2017.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.*;


public class Day02
{
   public static final URL INPUT_FILE_URL = Day02.class.getResource("input/input2.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      List <List <Integer>> spreadsheet = getSpreadSheet(INPUT_FILE_URL);
      System.out.println("A : " + checksumMinMax(spreadsheet));
      System.out.println("B : " + checksumDivisible(spreadsheet));
   }
   
   
   public static List <List <Integer>> getSpreadSheet(URL inputFileURL) throws IOException
   {
      return ReaderUtil.parseFileToList(
         inputFileURL,
         (line) -> Arrays.stream(line.split("\\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toUnmodifiableList())
      );
   }
   
   
   public static int checksumMinMax(List <List <Integer>> spreadsheet)
   {
      int sum = 0;
      
      for (List <Integer> row : spreadsheet)
      {
         int min = row.stream().mapToInt(i -> i).min().getAsInt();
         int max = row.stream().mapToInt(i -> i).max().getAsInt();
         
         sum += max - min;
      }
      
      return sum;
   }
   
   
   public static int checksumDivisible(List <List <Integer>> spreadsheet)
   {
      int sum = 0;
      
      for (List <Integer> row : spreadsheet)
      {
         boolean found = false;
         
         for (int i = 0; i < row.size() && !found; i++)
         {
            for (int j = i + 1; j < row.size() && !found; j++)
            {
               int iNum = row.get(i);
               int jNum = row.get(j);
               
               if (iNum % jNum == 0)
               {
                  sum += iNum / jNum;
                  found = true;
               }
               else if (jNum % iNum == 0)
               {
                  sum += jNum / iNum;
                  found = true;
               }
            }
         }
      }
      
      return sum;
   }
}
