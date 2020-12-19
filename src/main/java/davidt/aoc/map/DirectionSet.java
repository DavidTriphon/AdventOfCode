package davidt.aoc.map;

import java.util.*;


public class DirectionSet implements IDimensional
{
   // instance fields
   
   private final int _dimensionCount;
   
   private final Map <Integer, Direction> _directionMap = new HashMap <>();
   
   // constructor
   
   
   public DirectionSet(int dimensionCount, boolean includeCenter, boolean includeDiagonals)
   {
      _dimensionCount = dimensionCount;
      
      if (_dimensionCount <= 0)
         throw new IllegalArgumentException("dimensionCount must be positive.");
      
      if (includeCenter)
      {
         Polarity[] centerPols = new Polarity[dimensionCount];
         Arrays.fill(centerPols, Polarity.NEUTRAL);
         Direction centerDir = new Direction(centerPols);
         add(centerDir);
      }
      
      if (includeDiagonals)
      {
         ArrayList <Polarity> pols = new ArrayList <>();
         
         recursiveAddDimLayers(pols);
      }
      else
      {
         Polarity[] pols = new Polarity[dimensionCount];
         Arrays.fill(pols, Polarity.NEUTRAL);
         
         for (int dim = 0; dim < dimensionCount; dim++)
         {
            Direction dir;
            pols[dim] = Polarity.POSITIVE;
            dir = new Direction(pols);
            add(dir);
            pols[dim] = Polarity.NEGATIVE;
            dir = new Direction(pols);
            add(dir);
            
            pols[dim] = Polarity.NEUTRAL;
         }
      }
   }
   
   
   @Override
   public int dims()
   {
      return _dimensionCount;
   }
   
   // instance methods
   
   
   public Set <Direction> values()
   {
      return Set.copyOf(_directionMap.values());
   }
   
   
   public Direction getCenter()
   {
      return _directionMap.get(0);
   }
   
   
   public Direction getDirection(int dimension, Polarity polarity)
   {
      return _directionMap.get(Polarity.getNumeric(dimension, polarity));
   }
   
   
   public Direction getDirection(Polarity... polarities)
   {
      return _directionMap.get(Polarity.getNumeric(polarities));
   }
   
   // private helper functions
   
   
   private void recursiveAddDimLayers(ArrayList <Polarity> dims)
   {
      if (dims.size() == _dimensionCount)
      {
         Direction dir = new Direction(dims.toArray(new Polarity[0]));
         if (dir.getNumeric() != 0)
            add(dir);
      }
      else
      {
         for (Polarity pol : Polarity.values())
         {
            dims.add(pol);
            recursiveAddDimLayers(dims);
            dims.remove(dims.size() - 1);
         }
      }
   }
   
   
   private void add(Direction dir)
   {
      _directionMap.put(dir.getNumeric(), dir);
   }
   
   // inner declarations
   
   
   public class Direction implements IDimensional.Gettable <Polarity>
   {
      // instance fields
      
      private final Polarity[] _dirs;
      
      // constructors
      
      
      private Direction(Polarity[] dirs)
      {
         _dirs = Arrays.copyOf(dirs, _dimensionCount);
      }
      
      // implemented IDimensional methods
      
      
      @Override
      public int dims()
      {
         return _dimensionCount;
      }
      
      
      @Override
      public Polarity get(int dimensionIndex)
      {
         IDimensional.checkDimIndexArg(this, dimensionIndex);
         return _dirs[dimensionIndex];
      }
      
      
      @Override
      public Polarity[] get()
      {
         Polarity[] copy = new Polarity[dims()];
         System.arraycopy(_dirs, 0, copy, 0, dims());
         return copy;
      }
      
      // instance methods
      
      
      public int getNumeric()
      {
         return Polarity.getNumeric(_dirs);
      }
      
      
      public Direction getNeighbor(int fromDim, int toDim)
      {
         // TODO: IMPLEMENT
         return this;
      }
      
      
      public IDimensional.Gettable <Integer> intWrapper()
      {
         return new IDimensional.Gettable <>()
         {
            
            @Override
            public Integer get(int dimensionIndex)
            {
               return _dirs[dimensionIndex].magnitude;
            }
            
            
            @Override
            public Integer[] get()
            {
               Integer[] copy = new Integer[dims()];
               
               for (int i = 0; i < dims(); i++)
               {
                  copy[i] = _dirs[i].magnitude;
               }
               
               return copy;
            }
            
            
            @Override
            public int dims()
            {
               return _dimensionCount;
            }
         };
      }
   }
}
