package util;

import java.util.*;


public class SearchUtil
{
   public static int binarySearch(List <Integer> list, int start, int end, int target)
   {
      if (start + 1 >= end)
         return start;
      
      int middle = (start + end) / 2;
      
      if (list.get(middle) < target)
         return binarySearch(list, middle, end, target);
      else
         return binarySearch(list, start, middle, target);
   }
}
