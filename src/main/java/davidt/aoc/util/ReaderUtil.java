package davidt.aoc.util;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;


public class ReaderUtil
{
   public static String DEFAULT_DELIMITER = "\n";
   
   /// FILE TO STRING
   
   
   public static String getFileString(URL inputFileURL) throws IOException
   {
      return getInputScanner(inputFileURL, "\\A").next().trim();
   }
   
   // INPUT STREAM TO SCANNER
   
   
   public static Scanner getInputScanner(URL inputFileURL) throws IOException
   {
      return getInputScanner(inputFileURL, DEFAULT_DELIMITER);
   }
   
   
   public static Scanner getInputScanner(URL inputFileURL, String delimiter) throws IOException
   {
      Scanner inputIterator = new Scanner(new BufferedReader(
         new InputStreamReader(inputFileURL.openStream(), StandardCharsets.US_ASCII)));
      
      if (delimiter != null)
         inputIterator.useDelimiter(Pattern.compile(delimiter));
      
      return inputIterator;
   }
   
   /// CONSUMER PARSER
   
   
   public static void parseFileWithMethod(URL inputFileURL, Consumer <String> method)
      throws IOException
   {
      parseFileWithMethod(inputFileURL, DEFAULT_DELIMITER, method);
   }
   
   
   public static void parseFileWithMethod(
      URL inputFileURL, String delimiter, Consumer <String> method)
      throws IOException
   {
      Scanner inputReader = getInputScanner(inputFileURL, delimiter);
   
      while (inputReader.hasNext())
      {
         method.accept(inputReader.next().trim());
      }
   }
   
   /// FUNCTIONAL LIST PARSER
   
   
   public static List <String> parseFileToList(URL inputFileURL)
      throws IOException
   {
      return parseFileToList(inputFileURL, DEFAULT_DELIMITER, s -> s);
   }
   
   
   public static <T> List <T> parseFileToList(URL inputFileURL, Function <String, T> translator)
      throws IOException
   {
      return parseFileToList(inputFileURL, DEFAULT_DELIMITER, translator);
   }
   
   
   public static List <String> parseFileToList(URL inputFileURL, String delimiter)
      throws IOException
   {
      return parseFileToList(inputFileURL, delimiter, s -> s);
   }
   
   
   public static <T> List <T> parseFileToList(
      URL inputFileURL, String delimiter, Function <String, T> translator)
      throws IOException
   {
      Scanner inputReader = getInputScanner(inputFileURL, delimiter);
      
      ArrayList <T> results = new ArrayList <>();
      
      while (inputReader.hasNext())
      {
         results.add(translator.apply(inputReader.next().trim()));
      }
      
      return results;
   }
   
   /// FUNCTIONAL MAP PARSER
   
   
   public static <K, V> HashMap <K, V> parseFileToMap(
      URL inputFileURL, Function <String, V> translator, Function <V, K> keyMaker)
      throws IOException
   {
      return parseFileToMap(inputFileURL, DEFAULT_DELIMITER, translator, keyMaker);
   }
   
   
   public static <K, V> HashMap <K, V> parseFileToMap(
      URL inputFileURL, String delimiter, Function <String, V> translator, Function <V, K> keyMaker)
      throws IOException
   {
      Scanner inputReader = getInputScanner(inputFileURL, delimiter);
      
      HashMap <K, V> map = new HashMap <>();
      
      while (inputReader.hasNext())
      {
         V value = translator.apply(inputReader.next().trim());
         K key = keyMaker.apply(value);
         map.put(key, value);
      }
      
      return map;
   }
}
