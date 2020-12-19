package davidt.aoc.map;

import java.util.function.*;


public class Grid2DFlatPrinter <T> implements IDimensional
{
   private final Function <T, String> _translator;
   private final boolean              _yIsDown;
   
   
   public Grid2DFlatPrinter(Function <T, String> translator, boolean yIsDown)
   {
      _translator = translator;
      _yIsDown = yIsDown;
   }
   
   
   @Override
   public int dims()
   {
      return 2;
   }
   
   
   public String toMapString(GridMap <T, ?> map)
   {
      IDimensional.checkDimsMatch(this, map);
      
      // generate string grid using translator
      StringBuilder sb = new StringBuilder();
      
      Position lowerBound = map.getLowerBound();
      Position upperBound = map.getUpperBound();
      
      if (_yIsDown)
         for (int y = lowerBound.getY(); y < upperBound.getY(); y++)
         {
            mapStringHelperX(map, sb, y, lowerBound.getX(), upperBound.getX());
         }
      else
         for (int y = upperBound.getY() - 1; lowerBound.getY() <= y; y--)
         {
            mapStringHelperX(map, sb, y, lowerBound.getX(), upperBound.getX());
         }
      
      return sb.toString();
   }
   
   // private methods
   
   
   // for use by toMapString only
   private void mapStringHelperX(GridMap <T, ?> map, StringBuilder sb, int y, int minX, int maxX)
   {
      for (int x = minX; x < maxX; x++)
      {
         sb.append(_translator.apply(map.get(new Position(new int[] {x, y}))));
      }
      
      sb.append('\n');
   }
}
