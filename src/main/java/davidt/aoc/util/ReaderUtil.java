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
   
   public static Function <String, Boolean> DEFAULT_UNTIL = (s) -> false;
   
   /// FILE TO STRING
   
   
   public static String getScannerString(Scanner scanner)
   {
      scanner.useDelimiter(Pattern.compile("\\A"));
      return scanner.next().trim();
   }
   
   
   public static String getFileString(URL inputFileURL) throws IOException
   {
      return getInputScanner(inputFileURL, "\\A").next().trim();
   }
   
   /// URL TO SCANNER
   
   
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
   
   /// URL TO SCANNER FACTORY
   
   // (for parsing to lists of scanners, e.g. 2020 day 16)
   
   
   public static Function <String, Scanner> getScannerFactory(String delimiter)
   {
      return (inputString) -> {
         Scanner scanner = new Scanner(inputString);
         
         if (delimiter != null)
            scanner.useDelimiter(Pattern.compile(delimiter));
         
         return scanner;
      };
   }
   
   /// SCANNER CONSUMER
   
   
   public static void consumeScanner(Scanner scanner, Consumer <String> method)
   {
      method.accept(getScannerString(scanner));
   }
   
   /// SCANNER TRANSLATOR
   
   
   public static <T> T consumeScannerTo(Scanner scanner, Function <String, T> translator)
   {
      return translator.apply(getScannerString(scanner));
   }
   
   /// SCANNER ITERATOR
   
   
   public static void iterateScannerWithMethod(
      Scanner scanner, Consumer <String> method, Function <String, Boolean> until)
   {
      while (scanner.hasNext())
      {
         String next = scanner.next().trim();
         if (until.apply(next))
            break;
         method.accept(next);
      }
   }
   
   
   public static void iterateScannerWithMethod(Scanner scanner, Consumer <String> method)
   {
      iterateScannerWithMethod(scanner, method, DEFAULT_UNTIL);
   }
   
   
   public static <T> List <T> iterateScannerToList(
      Scanner scanner, Function <String, T> translator, Function <String, Boolean> until)
   {
      ArrayList <T> list = new ArrayList <>();
      
      iterateScannerWithMethod(scanner, (s) -> list.add(translator.apply(s)), until);
      
      return list;
   }
   
   
   public static <T> List <T> iterateScannerToList(
      Scanner scanner, Function <String, T> translator)
   {
      return iterateScannerToList(scanner, translator, DEFAULT_UNTIL);
   }
   
   
   public static <K, V> Map <K, V> iterateScannerToMap(
      Scanner scanner, Function <String, V> translator, Function <V, K> keyMaker,
      Function <String, Boolean> until)
   {
      HashMap <K, V> map = new HashMap <>();
      
      iterateScannerWithMethod(
         scanner,
         (s) -> {
            V value = translator.apply(s);
            K key = keyMaker.apply(value);
            map.put(key, value);
         },
         until
      );
      
      return map;
   }
   
   
   public static <K, V> Map <K, V> iterateScannerToMap(
      Scanner scanner, Function <String, V> translator, Function <V, K> keyMaker)
   {
      return iterateScannerToMap(scanner, translator, keyMaker, DEFAULT_UNTIL);
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
      iterateScannerWithMethod(getInputScanner(inputFileURL, delimiter), method);
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
      return iterateScannerToList(getInputScanner(inputFileURL, delimiter), translator);
   }
   
   /// FUNCTIONAL MAP PARSER
   
   
   public static <K, V> Map <K, V> parseFileToMap(
      URL inputFileURL, Function <String, V> translator, Function <V, K> keyMaker)
      throws IOException
   {
      return parseFileToMap(inputFileURL, DEFAULT_DELIMITER, translator, keyMaker);
   }
   
   
   public static <K, V> Map <K, V> parseFileToMap(
      URL inputFileURL, String delimiter, Function <String, V> translator, Function <V, K> keyMaker)
      throws IOException
   {
      return iterateScannerToMap(getInputScanner(inputFileURL, delimiter), translator, keyMaker);
   }
}
