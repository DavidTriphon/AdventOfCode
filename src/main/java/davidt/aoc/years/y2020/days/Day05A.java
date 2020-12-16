package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.boarding.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day05A
{
   public static final URL INPUT_FILE_LOC = Day05A.class.getResource("input/input5.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <BoardingPass> passes = ReaderUtil.parseFileToList(
         INPUT_FILE_LOC, BoardingPass::new
      );
   
      int maxID = 0;
   
      for (BoardingPass pass : passes)
      {
         if (pass.seatID > maxID)
            maxID = pass.seatID;
      }
   
      return maxID;
   }
}
