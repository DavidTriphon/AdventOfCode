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
      this(size);
      iterateDims((dim) -> _coordinates[dim] = fillWith);
   }
   
   
   public Position(int size)
   {
      this(new int[size]);
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
   public Integer get(int dim)
   {
      IDimensional.checkDimIndexArg(this, dim);
      return _coordinates[dim];
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
   public void set(int dim, Integer newValue)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] = newValue;
   }
   
   
   @Override
   public void set(Integer[] newPos)
   {
      iterateDims(Math.min(dims(), newPos.length), (dim) -> _coordinates[dim] = newPos[dim]);
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
   
      iterateDims(minDims, (dim) -> _coordinates[dim] = newPos.get(dim));
   }
   
   
   public void setAll(int uniformPos)
   {
      iterateDims((dim) -> _coordinates[dim] = uniformPos);
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
   
   
   public List <Position> listContainedPositions()
   {
      List <Position> positionList = new ArrayList <>();
      positionList.add(new Position(dims()));
      
      for (int dim = 0; dim < dims(); dim++)
      {
         for (Position pos : List.copyOf(positionList))
         {
            for (int distance = 1; distance < get(dim); distance++)
            {
               positionList.add(pos.copy().add(dim, distance));
            }
         }
      }
      
      return positionList;
   }
   
   // mutator methods
   
   
   public Position add(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> _coordinates[dim] += offset.get(dim));
      return this;
   }
   
   
   public Position add(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> _coordinates[dim] += scalar * offset.get(dim));
      return this;
   }
   
   
   public Position add(int offset)
   {
      iterateDims((dim) -> _coordinates[dim] += offset);
      return this;
   }
   
   
   public Position add(int dim, int offset)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] += offset;
      return this;
   }
   
   
   public Position subtract(IDimensional.Gettable <Integer> offset)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> _coordinates[dim] -= offset.get(dim));
      return this;
   }
   
   
   public Position subtract(IDimensional.Gettable <Integer> offset, int scalar)
   {
      IDimensional.checkDimsMatch(this, offset);
      iterateDims((dim) -> _coordinates[dim] -= scalar * offset.get(dim));
      return this;
   }
   
   
   public Position subtract(int offset)
   {
      iterateDims((dim) -> _coordinates[dim] -= offset);
      return this;
   }
   
   
   public Position subtract(int dim, int offset)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] -= offset;
      return this;
   }
   
   
   public Position multiply(IDimensional.Gettable <Integer> scalar)
   {
      IDimensional.checkDimsMatch(this, scalar);
      iterateDims((dim) -> _coordinates[dim] *= scalar.get(dim));
      return this;
   }
   
   
   public Position multiply(int scalar)
   {
      iterateDims((dim) -> _coordinates[dim] *= scalar);
      return this;
   }
   
   
   public Position multiply(int dim, int scalar)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] *= scalar;
      return this;
   }
   
   
   public Position divide(IDimensional.Gettable <Integer> denominator)
   {
      IDimensional.checkDimsMatch(this, denominator);
      iterateDims((dim) -> _coordinates[dim] /= denominator.get(dim));
      return this;
   }
   
   
   public Position divide(int denominator)
   {
      iterateDims((dim) -> _coordinates[dim] /= denominator);
      return this;
   }
   
   
   public Position divide(int dim, int denominator)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] /= denominator;
      return this;
   }
   
   
   public Position modulo(IDimensional.Gettable <Integer> denominator)
   {
      IDimensional.checkDimsMatch(this, denominator);
      iterateDims((dim) -> _coordinates[dim] %= denominator.get(dim));
      return this;
   }
   
   
   public Position modulo(int denominator)
   {
      iterateDims((dim) -> _coordinates[dim] %= denominator);
      return this;
   }
   
   
   public Position modulo(int dim, int denominator)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] %= denominator;
      return this;
   }
   
   
   public Position negate()
   {
      iterateDims((dim) -> _coordinates[dim] = -_coordinates[dim]);
      return this;
   }
   
   
   public Position negate(int dim)
   {
      IDimensional.checkDimIndexArg(this, dim);
      _coordinates[dim] = -_coordinates[dim];
      return this;
   }
   
   
   public Position max(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      iterateDims((dim) -> _coordinates[dim] = Math.max(_coordinates[dim], pos.get(dim)));
      return this;
   }
   
   
   public Position min(IDimensional.Gettable <Integer> pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      iterateDims((dim) -> _coordinates[dim] = Math.min(_coordinates[dim], pos.get(dim)));
      return this;
   }
   
   // methods that return new Positions
   
   
   public Position copy()
   {
      return new Position(this);
   }
   
   
   public Position withDims(int dims)
   {
      return new Position(dims, this);
   }
   
   // private helper methods
   
   
   private void iterateDims(Consumer <Integer> bodyMethod)
   {
      iterateDims(_coordinates.length, bodyMethod);
   }
   
   // public static methods
   
   
   public static int getIndexOfPosIn(
      Position innerPos, Position bounds)
   {
      IDimensional.checkDimsMatch(innerPos, bounds);
   
      if (!bounds.copy().subtract(1).copy().max(innerPos).equals(bounds.copy().subtract(1)))
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
}
