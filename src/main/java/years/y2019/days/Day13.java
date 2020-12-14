package years.y2019.days;

import years.y2019.arcade.*;

import java.io.*;


public class Day13
{
   
   public static void main(String... args) throws IOException
   {
      Arcade arcade = new Arcade();
      
      arcade.run();
      
      System.out.println(arcade.getScreen().count().get(Arcade.TILE_BLOCK));
   }
}
