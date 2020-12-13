package main.years.y2020.days;

import main.util.*;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;


public class Day12B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input12.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <Map.Entry <Character, Integer>> instructions =
         ReaderUtil.parseFileToList(
            INPUT_FILE_LOC,
            (string) -> Map.entry(string.charAt(0), Integer.parseInt(string.substring(1)))
         );
      
      Point shipPos = new Point();
      
      Point waypoint = new Point(10, 1);
      
      for (Map.Entry <Character, Integer> instruction : instructions)
      {
         switch (instruction.getKey())
         {
            case 'N':
               waypoint.y += instruction.getValue();
               break;
            case 'S':
               waypoint.y -= instruction.getValue();
               break;
            case 'E':
               waypoint.x += instruction.getValue();
               break;
            case 'W':
               waypoint.x -= instruction.getValue();
               break;
            case 'L':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  int temp = waypoint.y;
                  waypoint.y = waypoint.x;
                  waypoint.x = -temp;
               }
               break;
            case 'R':
               for (int i = 0; i < instruction.getValue() / 90; i++)
               {
                  int temp = waypoint.x;
                  waypoint.x = waypoint.y;
                  waypoint.y = -temp;
               }
               break;
            case 'F':
               shipPos.translate(
                  waypoint.x * instruction.getValue(),
                  waypoint.y * instruction.getValue()
               );
               break;
         }
      }
      
      return Math.abs(shipPos.x) + Math.abs(shipPos.y);
   }
}
