package davidt.aoc.map;

import java.util.*;
import java.util.function.*;


public class Position implements IDimensional.Settable <Integer>
{
   // instance fields
   
   private final int[] _coordinates;
   
   // constructor
   
   
   public Position(int[] coordinates)
   {
      _coordinates = Arrays.copyOf(coordinates, coordinates.length);
   }
   
   
   public Position(Position pos)
   {
      this(pos._coordinates);
   }
   
   
   public Position(int size, int[] coordinates)
   {
      this(size);
      System.arraycopy(coordinates, 0, _coordinates, 0,
         Math.min(coordinates.length, _coordinates.length)
      );
   }
   
   
   public Position(int size, Position pos)
   {
      this(size);
      set(pos);
   }
   
   
   public Position(int size, int fillWith)
   {
      this(iterateDims(size, (dim) -> fillWith));
   }
   
   
   public Position(int size)
   {
      this(size, 0);
   }
   
   // object overrides
   
   
   @Override
   public int hashCode()
   {
      // If using position hashes, do not change the positions!
      // Use copy if you need to alter some other state, but the keys must not change.
      return Arrays.hashCode(_coordinates);
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof Position))
         return false;
      Position position = (Position) o;
      return Arrays.equals(_coordinates, position._coordinates);
   }
   
   
   @Override
   public String toString()
   {
      return "Position{" + Arrays.toString(_coordinates) + "}";
   }
   
   // implemented IDimensional methods
   
   
   @Override
   public int dims()
   {
      return _coordinates.length;
   }
   
   
   @Override
   public Integer get(int dimensionIndex)
   {
      IDimensional.checkDimIndexArg(this, dimensionIndex);
      return _coordinates[dimensionIndex];
   }
   
   
   @Override
   public Integer[] get()
   {
      Integer[] copy = new Integer[dims()];
      for (int i = 0; i < dims(); i++)
      {
         copy[i] = get(i);
      }
      
      return copy;
   }
   
   
   @Override
   public void set(int dimensionIndex, Integer newValue)
   {
      IDimensional.checkDimIndexArg(this, dimensionIndex);
      _coordinates[dimensionIndex] = newValue;
   }
   
   
   @Override
   public void set(Integer[] newPos)
   {
      int minDims = Math.min(dims(), newPos.length);
      
      iterateDims(minDims, (dim) -> {
         _coordinates[dim] = newPos[dim];
      });
   }
   
   // getters and getters
   
   
   public int getX()
   {
      return get(0);
   }
   
   
   public void setX(int newValue)
   {
      set(0, newValue);
   }
   
   
   public int getY()
   {
      return get(1);
   }
   
   
   public void setY(int newValue)
   {
      set(1, newValue);
   }
   
   
   public int getZ()
   {
      return get(2);
   }
   
   
   public void setZ(int newValue)
   {
      set(2, newValue);
   }
   
   
   public void set(IDimensional.Gettable <Integer> newPos)
   {
      int minDims = Math.min(dims(), newPos.dims());
      
      iterateDims(minDims, (dim) -> {
         _coordinates[dim] = newPos.get(dim);
      });
   }
   
   
   public void setAll(int uniformPos)
   {
      iterateDims((dim) -> {
         _coordinates[dim] = uniformPos;
      });
   }
   
   // calculator getters
   
   
   public int calcTaxicabDistance()
   {
      int sum = 0;
      
      for (int coordinate : _coordinates)
      {
         sum += Math.abs(coordinate);
      }
      
      return sum;
   }
   
   
   public int calcChessboardDistance()
   {
      int max = 0;
      
      for (int coordinate : _coordinates)
      {
         max = Math.max(Math.abs(coordinate), max);
      }
      
      return max;
   }
   
   
   public int calcContainedSpace()
   {
      int sum = 1;
      
      for (int coordinate : _coordinates)
      {
         sum *= coordinate;
      }
      
      return sum;
   }
   
   
   public boolean isOrigin()
   {
      for (int coord : _coordinates)
      {
         if (coord != 0)
            return false;
      }
      return true;
   }
   
   // mutator methods
   
   
   public void addBy(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> {
         _coordinates[dim] += offset.get(dim);
      });
   }
   
   
   public void addBy(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> {
         _coordinates[dim] += scalar * offset.get(dim);
      });
   }
   
   
   public void addBy(int offset)
   {
      iterateDims((dim) -> {
         _coordinates[dim] += offset;
      });
   }
   
   
   public void subtractBy(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> {
         _coordinates[dim] -= offset.get(dim);
      });
   }
   
   
   public void subtractBy(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> {
         _coordinates[dim] -= scalar * offset.get(dim);
      });
   }
   
   
   public void subtractBy(int offset)
   {
      iterateDims((dim) -> {
         _coordinates[dim] -= offset;
      });
   }
   
   
   public void multiplyBy(int scalar)
   {
      iterateDims((dim) -> {
         _coordinates[dim] *= scalar;
      });
   }
   
   
   public void divideBy(int denominator)
   {
      iterateDims((dim) -> {
         _coordinates[dim] /= denominator;
      });
   }
   
   
   public void moduloBy(int denominator)
   {
      iterateDims((dim) -> {
         _coordinates[dim] %= denominator;
      });
   }
   
   
   public void makeOpposite()
   {
      iterateDims((dim) -> {
         _coordinates[dim] = -_coordinates[dim];
      });
   }
   
   
   public void makeOpposite(int dimensionIndex)
   {
      IDimensional.checkDimIndexArg(this, dimensionIndex);
      _coordinates[dimensionIndex] = -_coordinates[dimensionIndex];
   }
   
   
   public void makeMax(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      iterateDims((dim) -> {
         _coordinates[dim] = Math.max(_coordinates[dim], pos.get(dim));
      });
   }
   
   
   public void makeMin(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      iterateDims((dim) -> {
         _coordinates[dim] = Math.min(_coordinates[dim], pos.get(dim));
      });
   }
   
   // methods that return new Positions
   
   
   public Position sumWith(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      return new Position(iterateDims((dim) ->
         _coordinates[dim] + offset.get(dim)
      ));
   }
   
   
   public Position sumWith(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      return new Position(iterateDims((dim) ->
         _coordinates[dim] + scalar * offset.get(dim)
      ));
   }
   
   
   public Position sumWith(int offset)
   {
      return new Position(iterateDims((dim) ->
         _coordinates[dim] + offset
      ));
   }
   
   
   public Position differenceWith(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      return new Position(iterateDims((dim) ->
         _coordinates[dim] - offset.get(dim)
      ));
   }
   
   
   public Position differenceWith(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      return new Position(iterateDims((dim) ->
         _coordinates[dim] - scalar * offset.get(dim)
      ));
   }
   
   
   public Position differenceWith(int offset)
   {
      return new Position(iterateDims((dim) ->
         _coordinates[dim] - offset
      ));
   }
   
   
   public Position productOf(int scalar)
   {
      return new Position(iterateDims((dim) ->
         _coordinates[dim] * scalar
      ));
   }
   
   
   public Position quotientOf(int denominator)
   {
      return new Position(iterateDims((dim) ->
         _coordinates[dim] / denominator
      ));
   }
   
   
   public Position remainderOf(int denominator)
   {
      return new Position(iterateDims((dim) ->
         _coordinates[dim] % denominator
      ));
   }
   
   
   public Position getOpposite()
   {
      return new Position(iterateDims((dim) ->
         -_coordinates[dim]
      ));
   }
   
   
   public Position getOpposite(int dimensionIndex)
   {
      // checkDim is already called in copy.makeOpposite.
      // if you remove that method call, uncomment the next code line.
      // IDimensional.checkDimIndexArg(this, dimensionIndex);
      Position copy = new Position(this);
      copy.makeOpposite(dimensionIndex);
      return copy;
   }
   
   
   public Position getMax(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      return new Position(iterateDims((dim) -> {
         return Math.max(_coordinates[dim], pos.get(dim));
      }));
   }
   
   
   public Position getMin(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      return new Position(iterateDims((dim) -> {
         return Math.min(_coordinates[dim], pos.get(dim));
      }));
   }
   
   // private helper methods
   
   
   private void iterateDims(Consumer <Integer> bodyMethod)
   {
      iterateDims(_coordinates.length, bodyMethod);
   }
   
   
   private int[] iterateDims(Function <Integer, Integer> bodyMethod)
   {
      return iterateDims(_coordinates.length, bodyMethod);
   }
   
   // public static methods
   
   
   public static int getIndexOfPosIn(
      Position innerPos, Position bounds)
   {
      IDimensional.checkDimsMatch(innerPos, bounds);
      
      if (!bounds.differenceWith(1).getMax(innerPos).equals(bounds.differenceWith(1)))
         throw new IllegalArgumentException(String.format(
            "innerPos cannot exceed bounds in any dimension. innerPos = %s, bounds = %s",
            innerPos, bounds
         ));
      
      int index = 0;
      int scalar = 1;
      
      for (int dim = 0; dim < bounds.dims(); dim++)
      {
         index += scalar * innerPos.get(dim);
         scalar *= bounds.get(dim);
      }
      
      return index;
   }
   
   
   public static Position getPosOfIndexIn(int index, Position bounds)
   {
      if (index < 0)
         throw new IllegalArgumentException("index must be a positive number. index = " + index);
      if (index >= bounds.calcContainedSpace())
         throw new IllegalArgumentException(String.format(
            "index cannot exceed the number of spaces contained by the space. index = %d, pos = " +
            "%s, space = %d",
            index, bounds, bounds.calcContainedSpace()
         ));
      
      int[] coords = new int[bounds.dims()];
      
      for (int dim = 0; dim < bounds.dims(); dim++)
      {
         coords[dim] = index % bounds.get(dim);
         index /= bounds.get(dim);
      }
      
      return new Position(coords);
   }
   
   // static private helper methods
   
   
   private static void iterateDims(int dimCount, Consumer <Integer> bodyMethod)
   {
      for (int dim = 0; dim < dimCount; dim++)
      {
         bodyMethod.accept(dim);
      }
   }
   
   
   private static int[] iterateDims(int dimCount, Function <Integer, Integer> bodyMethod)
   {
      int[] resultCoords = new int[dimCount];
      
      for (int dim = 0; dim < dimCount; dim++)
      {
         resultCoords[dim] = bodyMethod.apply(dim);
      }
      
      return resultCoords;
   }
}
