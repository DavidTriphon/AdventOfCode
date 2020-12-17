package davidt.aoc.years.y2020.days;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static davidt.aoc.years.y2020.days.Day16.*;
import static org.junit.jupiter.api.Assertions.*;


class Day16Test
{
   public static final URL EXAMPLE_1 = Day16Test.class.getResource("input/16/example1.txt");
   public static final URL EXAMPLE_2 = Day16Test.class.getResource("input/16/example2.txt");
   
   
   @Test
   void exampleParsing() throws IOException
   {
      TicketData ticketData = parseURL(EXAMPLE_1);
      assertEquals(3, ticketData.fieldRules.size());
      assertEquals(3, ticketData.myTicket.size());
      assertEquals(4, ticketData.nearbyTickets.size());
   }
   
   
   @Test
   void part1Examples() throws IOException
   {
      TicketData ticketData = parseURL(EXAMPLE_1);
      assertEquals(71, countErrorRate(ticketData));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      TicketData ticketData = parseURL(INPUT_FILE_LOC);
      assertEquals(30869, countErrorRate(ticketData));
   }
   
   
   @Test
   void part2Removal() throws IOException
   {
      TicketData ticketData = parseURL(EXAMPLE_1);
      removeBadTickets(ticketData);
      assertEquals(0, countErrorRate(ticketData));
      
      ticketData = parseURL(INPUT_FILE_LOC);
      removeBadTickets(ticketData);
      assertEquals(0, countErrorRate(ticketData));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      TicketData ticketData = parseURL(EXAMPLE_2);
      Map <FieldRule, Integer> ruleOrder = findRulePositions(ticketData);
      FieldRule classField = ticketData.fieldRules.get(0);
      FieldRule rowField = ticketData.fieldRules.get(1);
      FieldRule seatField = ticketData.fieldRules.get(2);
      
      assertEquals(0, ruleOrder.get(rowField));
      assertEquals(1, ruleOrder.get(classField));
      assertEquals(2, ruleOrder.get(seatField));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      TicketData ticketData = parseURL(INPUT_FILE_LOC);
      removeBadTickets(ticketData);
      assertEquals(
         4381476149273L,
         findDepartureMultiple(ticketData, findRulePositions(ticketData))
      );
   }
}
