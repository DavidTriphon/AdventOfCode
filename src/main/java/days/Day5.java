package days;

import boarding.*;
import util.*;

import java.io.*;
import java.util.*;


public class Day5
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input5.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      List <BoardingPass> passes = ReaderUtil.parseFileToList(
         INPUT_FILE_LOC, BoardingPass::new
      );
      
      long maxID = 0;
      
      for (BoardingPass pass : passes)
      {
         if (pass.seatID > maxID)
            maxID = pass.seatID;
      }
   
      System.out.println(maxID);
   }
}
