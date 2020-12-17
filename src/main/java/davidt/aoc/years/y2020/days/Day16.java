package davidt.aoc.years.y2020.days;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;

import static davidt.aoc.util.ReaderUtil.*;


public class Day16
{
   public static final URL INPUT_FILE_LOC = Day16.class.getResource("input/input16.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      TicketData ticketData = parseURL(INPUT_FILE_LOC);
      
      System.out.println("A : " + countErrorRate(ticketData));
      removeBadTickets(ticketData);
      System.out.println("B : " + findDepartureMultiple(ticketData, findRulePositions(ticketData)));
   }
   
   
   public static int countErrorRate(TicketData ticketData)
   {
      int errorRate = 0;
      
      for (List <Integer> ticket : ticketData.nearbyTickets)
      {
         for (int value : ticket)
         {
            boolean isValid = false;
            
            for (FieldRule rule : ticketData.fieldRules)
            {
               if (rule.isValid(value))
               {
                  isValid = true;
                  break;
               }
            }
            
            if (!isValid)
            {
               errorRate += value;
               break;
            }
         }
      }
      
      return errorRate;
   }
   
   
   public static void removeBadTickets(TicketData ticketData)
   {
      ArrayList <List <Integer>> list = new ArrayList <>(ticketData.nearbyTickets);
      
      for (int i = 0; i < list.size(); i++)
      {
         List <Integer> ticket = list.get(i);
         for (int value : ticket)
         {
            boolean isValid = false;
            
            for (FieldRule rule : ticketData.fieldRules)
            {
               if (rule.isValid(value))
               {
                  isValid = true;
                  break;
               }
            }
            
            if (!isValid)
            {
               list.remove(i--);
               break;
            }
         }
      }
      
      ticketData.nearbyTickets = list;
   }
   
   
   public static Map <FieldRule, Integer> findRulePositions(TicketData ticketData)
   {
      List <Set <FieldRule>> ruleOrder = new ArrayList <>();
      for (int i = 0; i < ticketData.fieldRules.size(); i++)
      {
         ruleOrder.add(new HashSet <>());
      }
      
      HashMap <FieldRule, Integer> rulePositions = new HashMap <>();
      
      ArrayList <FieldRule> availableRules = new ArrayList <>(ticketData.fieldRules);
      
      for (int column = 0; column < ticketData.myTicket.size(); column++)
      {
         ArrayList <FieldRule> possibleRules = new ArrayList <>(availableRules);
         
         for (int i = 0; i < ticketData.nearbyTickets.size(); i++)
         {
            int value = ticketData.nearbyTickets.get(i).get(column);
            
            for (int fieldI = 0; fieldI < possibleRules.size(); fieldI++)
            {
               FieldRule rule = possibleRules.get(fieldI);
               
               if (!rule.isValid(value))
                  possibleRules.remove(fieldI--);
            }
         }
         
         if (possibleRules.size() == 1)
         {
            FieldRule finalRule = possibleRules.get(0);
            rulePositions.put(finalRule, column);
            availableRules.remove(finalRule);
            ruleOrder.get(column).add(finalRule);
         }
         else if (!possibleRules.isEmpty())
         {
            for (FieldRule rule : possibleRules)
            {
               ruleOrder.get(column).add(rule);
            }
         }
         else
         {
            throw new IllegalStateException("There are 0 possible rules for column " + column);
         }
      }
      
      while (rulePositions.size() != ticketData.fieldRules.size())
      {
         // then some columns were not determined and must be determined through elimination
         for (int column = 0; column < ruleOrder.size(); column++)
         {
            Set <FieldRule> rules = ruleOrder.get(column);
            
            if (rules.size() > 1)
            {
               rules.removeIf((r) -> !availableRules.contains(r));
               
               if (rules.size() == 1)
               {
                  FieldRule finalRule = rules.iterator().next();
                  rulePositions.put(finalRule, column);
                  availableRules.remove(finalRule);
               }
            }
         }
      }
      
      return rulePositions;
   }
   
   
   public static long findDepartureMultiple(
      TicketData ticketData, Map <FieldRule, Integer> ruleOrder)
   {
      String prefixFilter = "departure";
      
      long multiple = 1;
      
      for (FieldRule key : ruleOrder.keySet())
      {
         if (key.name.startsWith(prefixFilter))
            multiple *= ticketData.myTicket.get(ruleOrder.get(key));
      }
      
      return multiple;
   }
   
   
   public static TicketData parseURL(URL inputURL) throws IOException
   {
      TicketData ticketData = new TicketData();
      
      // get scanners for each section of the input
      List <Scanner> scanners = iterateScannerToList(
         getInputScanner(inputURL, "\n\n[a-z ]+:\n"), getScannerFactory(DEFAULT_DELIMITER)
      );
      
      // get the rules
      ticketData.fieldRules = iterateScannerToList(scanners.get(0), FieldRule::parseString);
      
      // get the main ticket
      Scanner myTicketScanner = scanners.get(1);
      myTicketScanner.useDelimiter(",");
      ticketData.myTicket = iterateScannerToList(myTicketScanner, Integer::parseInt);
      
      // get the other tickets
      Function <String, Scanner> scannerFactory = getScannerFactory(",");
      ticketData.nearbyTickets = iterateScannerToList(
         scanners.get(2), (s) -> iterateScannerToList(scannerFactory.apply(s), Integer::parseInt)
      );
      
      return ticketData;
   }
   
   
   public static class FieldRule
   {
      public static final String RULE_REGEX = "([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)";
      
      public static final Pattern RULE_PATTERN = Pattern.compile(RULE_REGEX);
      
      public final String name;
      public final int    min1, max1, min2, max2;
      
      
      public FieldRule(String name, int min1, int max1, int min2, int max2)
      {
         this.name = name;
         this.min1 = min1;
         this.max1 = max1;
         this.min2 = min2;
         this.max2 = max2;
      }
      
      
      public boolean isValid(int value)
      {
         return (min1 <= value && value <= max1) || (min2 <= value && value <= max2);
      }
      
      
      @Override
      public int hashCode()
      {
         return Objects.hash(name, min1, max1, min2, max2);
      }
      
      
      @Override
      public boolean equals(Object o)
      {
         if (this == o)
            return true;
         if (!(o instanceof FieldRule))
            return false;
         FieldRule fieldRule = (FieldRule) o;
         return min1 == fieldRule.min1 && max1 == fieldRule.max1 && min2 == fieldRule.min2 &&
                max2 == fieldRule.max2 && name.equals(fieldRule.name);
      }
      
      
      public static FieldRule parseString(String input)
      {
         Matcher m = RULE_PATTERN.matcher(input);
         
         if (m.matches())
         {
            String name = m.group(1);
            
            int min1 = Integer.parseInt(m.group(2));
            int max1 = Integer.parseInt(m.group(3));
            int min2 = Integer.parseInt(m.group(4));
            int max2 = Integer.parseInt(m.group(5));
            
            return new FieldRule(name, min1, max1, min2, max2);
         }
         
         throw new IllegalArgumentException("input String does not match rule regex pattern.");
      }
   }
   
   
   public static class TicketData
   {
      public List <FieldRule>      fieldRules;
      public List <Integer>        myTicket;
      public List <List <Integer>> nearbyTickets;
   }
}
