package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.boarding.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day05B
{
   public static final URL INPUT_FILE_LOC = Day05B.class.getResource("input/input5.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <BoardingPass> passes = ReaderUtil.parseFileToList(
         INPUT_FILE_LOC, BoardingPass::new
      );
      
      passes.sort(Comparator.comparingInt((p) -> p.seatID));
      
      int index = 0;
      int nextID;
      do
      {
         nextID = passes.get(index++).seatID + 1;
      }
      while (passes.get(index).seatID == nextID);
      
      return nextID;
   }
}
