package years.y2020.days;

import util.*;
import years.y2020.luggage.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;


public class Day07B
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input7.txt";
   
   public static final String BAG_REGEX   = "([\\sa-z]+) bags?";
   public static final String COUNT_REGEX = "(\\d+) " + BAG_REGEX;
   public static final String RULE_REGEX  =
      String.format("%s contain ((%s,? ?)+).", BAG_REGEX, COUNT_REGEX);
   public static final String EMPTY_REGEX = String.format("%s contain no other bags.", BAG_REGEX);
   
   public static final Pattern RULE_PATTERN  = Pattern.compile(RULE_REGEX);
   public static final Pattern COUNT_PATTERN = Pattern.compile(COUNT_REGEX);
   public static final Pattern EMPTY_PATTERN = Pattern.compile(EMPTY_REGEX);
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      Map <String, ColoredBag> bagRuleMap = ReaderUtil.parseFileToMap(
         INPUT_FILE_LOC, (line) ->
         {
            Matcher emptyMatcher = EMPTY_PATTERN.matcher(line);
            Matcher ruleMatcher = RULE_PATTERN.matcher(line);
            
            ColoredBag bag;
            
            if (emptyMatcher.matches())
            {
               String color = emptyMatcher.group(1);
               bag = new ColoredBag(color);
            }
            else if (ruleMatcher.matches())
            {
               String color = ruleMatcher.group(1);
               bag = new ColoredBag(color);
               
               String[] innerBags = ruleMatcher.group(2).split(", ");
               
               for (String innerBag : innerBags)
               {
                  Matcher bagMatcher = COUNT_PATTERN.matcher(innerBag);
                  //noinspection ResultOfMethodCallIgnored
                  bagMatcher.matches();
                  int innerCount = Integer.parseInt(bagMatcher.group(1));
                  String innerColor = bagMatcher.group(2);
                  
                  bag.addBags(innerColor, innerCount);
               }
            }
            else
            {
               throw new IllegalStateException("Doesn't match either pattern.");
            }
            
            return bag;
         },
         ColoredBag::getColor
      );
      
      ColoredBag shinyGoldBag = bagRuleMap.get("shiny gold");
      
      return shinyGoldBag.containedBagCount(bagRuleMap);
   }
}
