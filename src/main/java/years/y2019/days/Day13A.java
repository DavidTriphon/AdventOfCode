package years.y2019.days;

import years.y2019.arcade.*;

import java.io.*;


public class Day13A
{
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      Arcade arcade = new Arcade();
      
      arcade.run();
      
      return arcade.getScreen().countOf(Arcade.TILE_BLOCK);
   }
}
