package davidt.aoc.map;

public interface IDimensional
{
   int dims();
   
   static void checkDimIndexArg(IDimensional obj, int dimensionIndex)
   {
      if (dimensionIndex >= obj.dims())
      {
         throw new IllegalArgumentException(String
            .format("A %dD object does not have a dimension at index %d!", obj.dims(),
               dimensionIndex
            ));
      }
      
      if (dimensionIndex < 0)
      {
         throw new IllegalArgumentException(
            String.format("Dimension indices cannot be negative! (%d)", dimensionIndex)
         );
      }
   }
   
   static void checkDimsMatch(IDimensional obj1, IDimensional obj2)
   {
      if (obj1.dims() != obj2.dims())
         throw new IllegalArgumentException(
            String.format("%s and %s do not have the same number of dimensions!", obj1, obj2)
         );
   }
   
   static void checkDimsMatch(IDimensional obj1, Object[] obj2)
   {
      if (obj1.dims() != obj2.length)
         throw new IllegalArgumentException(
            String.format("%s and %s do not have the same number of dimensions!", obj1, obj2)
         );
   }
   
   interface Gettable <T> extends IDimensional
   {
      T get(int dimensionIndex);
      
      T[] get();
   }
   
   
   interface Settable <T> extends Gettable <T>
   {
      void set(int dimensionIndex, T value);
      
      void set(T[] values);
   }
}
