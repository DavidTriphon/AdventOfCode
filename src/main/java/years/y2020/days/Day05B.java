package years.y2020.days;

import boarding.*;
import util.*;

import java.io.*;
import java.util.*;


public class Day05B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input5.txt";
   
   
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
