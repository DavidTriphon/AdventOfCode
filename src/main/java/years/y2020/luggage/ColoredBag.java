package years.y2020.luggage;

import java.util.*;


public class ColoredBag
{
   private String                    _color;
   private HashMap <String, Integer> _innerBags = new HashMap <>();
   
   
   public ColoredBag(String color)
   {
      this._color = color;
   }
   
   
   public void addBags(String color, int number)
   {
      _innerBags.put(color, number);
   }
   
   
   public String getColor()
   {
      return _color;
   }
   
   
   public Map <String, Integer> getInnerBags()
   {
      return Collections.unmodifiableMap(_innerBags);
   }
   
   
   public boolean containsGoldBag(Map <String, ColoredBag> bagRuleMap)
   {
      for (String innerColor : getInnerBags().keySet())
      {
         if (innerColor.equals("shiny gold"))
            return true;
         
         if (bagRuleMap.get(innerColor).containsGoldBag(bagRuleMap))
            return true;
      }
      
      return false;
   }
   
   
   public int containedBagCount(Map <String, ColoredBag> bagRuleMap)
   {
      int containedBagCount = 0;
      
      for (String innerColor : getInnerBags().keySet())
      {
         containedBagCount += _innerBags.get(innerColor)
            * (bagRuleMap.get(innerColor).containedBagCount(bagRuleMap) + 1);
      }
      
      return containedBagCount;
   }
   
   
   @Override
   public String toString()
   {
      return "ColoredBag{" +
         "color='" + _color + "';" +
         " containedBags=" + _innerBags +
         '}';
   }
}
