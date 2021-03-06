package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day08A
{
   private static final URL INPUT_FILE_LOC = Day08A.class.getResource("input/input8.txt");
   
   private static final int IMAGE_HEIGHT = 6;
   private static final int IMAGE_WIDTH  = 25;
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      ArrayList <String> layers = getLayersFromImage(fileString);
      
      String chosenLayer = getLayerWithLeastZeroes(layers);
      
      int onesCount = getOccurenceOfDigit(chosenLayer, '1');
      int twosCount = getOccurenceOfDigit(chosenLayer, '2');
      
      return onesCount * twosCount;
   }
   
   
   private static String getLayerWithLeastZeroes(ArrayList <String> layers)
   {
      String chosenLayer = null;
      int minZeroCount = Integer.MAX_VALUE;
      
      for (String layerString : layers)
      {
         int zeroCount = getOccurenceOfDigit(layerString, '0');
         
         if (zeroCount < minZeroCount)
         {
            minZeroCount = zeroCount;
            chosenLayer  = layerString;
         }
      }
      
      return chosenLayer;
   }
   
   
   private static ArrayList <String> getLayersFromImage(String imageString)
   {
      ArrayList <String> layers = new ArrayList <>();
      
      int startIndex = 0;
      int layerSize = IMAGE_HEIGHT * IMAGE_WIDTH;
      
      while (startIndex < imageString.length())
      {
         layers.add(imageString.substring(startIndex, startIndex + layerSize));
         startIndex += layerSize;
      }
      
      return layers;
   }
   
   
   private static int getOccurenceOfDigit(String layerString, char digit)
   {
      int count = 0;
      
      for (int i = 0; i < layerString.length(); i++)
      {
         if (layerString.charAt(i) == digit)
            count++;
      }
      
      return count;
   }
}
