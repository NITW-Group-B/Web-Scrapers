
package com.dellproject.scraper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.net.URL;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.*;
// import net.bytepowered.common.lang.HashMap;
// import okhttp3.benchmarks.UrlConnection;

// https://www.codegrepper.com/code-examples/python/how+to+scrape+.ashx
public class App {


  public static String downloadLink;
  public static String outputName;

  public static int stopFlag = 0;
  


  public static void main(String[] args) {

    //System.out.println( "Obtaining Data:" );
    try {

      PrintWriter  output = new PrintWriter( "CruiseMapperOutput.csv" );
      //PrintWriter  output2 = new PrintWriter( "RoyalCaribbeanOutput.csv");
      HashMap<String,String> variable = new HashMap<String, String>();
      variable.put("type", "application/x-google-chrome-pdf");
        // Here we create a document object and use JSoup to fetch the website

        //Here begins the code to scrape the table from cruisemapper.com
      Document doc = Jsoup.connect("https://www.cruisemapper.com/wiki/759-how-much-does-a-cruise-ship-cost").get();
      

      // fred started this
      // 2d array for 1 table
      ArrayList<ArrayList<String>> downServers = new ArrayList<ArrayList<String>>();
      
      
      Element table = doc.select("table").get(0); //select the first table
      Elements head = table.select("th"); // get the table heading
      Elements rows = table.select("tr"); // get the rest of the content in a table
      String headerPart = "";
      for (int l = 0; l < head.size(); l++){
        headerPart += head.get(l).text();
        if(l < head.size() - 1){
          headerPart += ", ";
        }
         
      // output.println(head.text());
       }
       output.println(headerPart);


      for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.

          ArrayList<String> myRow = new ArrayList<String>();
          Element row = rows.get(i);
          Elements cols = row.select("td");
          // loop through columns
          for(int j = 0; j < cols.size(); j++) {
            myRow.add(cols.get(j).text());
          }
          downServers.add(myRow);
      }
     

      for (int j = 0; j < downServers.size(); j++) {
        String line = "";
        for(int k = 0; k < downServers.get(j).size(); k++) {
          //Instead of println, figure out how to add a comma and space after each item so it's in a row instead of a column

          // remove commas from each entry
          downServers.get(j).get(k).replace(",", " ");
          line += downServers.get(j).get(k).replace(",", "");
          if(k < downServers.get(j).size() - 1) {
            line += ", ";
          }
          //  + ", ";
        }
        output.print(line);
        output.println();
      }
      
      // Time Period:Dec 31, 2014 - Apr 27, 2021 | Show:Historical Prices | Frequency:Weekly
      // https://finance.yahoo.com/quote/RCL/history?period1=1420070400&period2=1619568000&interval=1wk&filter=history&frequency=1wk&includeAdjustedClose=true"
      
      
      
      
      
    // handles IO exceptions
      
      // output2.print(yahooCruise);
      // try  {
      //   PrintWriter writer = new PrintWriter(new File("test.txt"));
      //   StringBuilder sb = new StringBuilder();
      //   sb.append("id,");
      //   sb.append(',');
      //   sb.append("Name");
      //   sb.append('\n');
  
      //   sb.append("1");
      //   sb.append(',');
      //   sb.append("Prashant Ghimire");
      //   sb.append('\n');
  
      //   String x = "I am a test";
      //   writer.write(x);
      //   System.out.print(sb);
      //   System.out.println("done!");
  
      // } catch (FileNotFoundException e) {
      //   System.out.println(e.getMessage());
      // }
      
      


      output.close();
      //output2.close();
      

      Files.move(Paths.get("C:/Users/Heather/Documents/GitHub/Web-Scrapers/dellprojectscraper/CruiseMapperOutput.csv"), Paths.get("C:/Users/Heather/Documents/GitHub/Web-Scrapers/dellprojectscraper/Output/CruiseMapperOutput.csv"), StandardCopyOption.REPLACE_EXISTING);
      
      
      
      
       
      // In case of any IO errors, we want the messages written to the console
      } catch (IOException e) {
        e.printStackTrace();
      }

      runLoop();
      
  
  }
  
  public static /*long*/void computeDateSeconds(Integer yearNumber, Integer monthNumber, Integer dayNumber) {
    
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-m-d");
    // LocalDateTime dateTime = DateTime.parse(yearNumber.toString() + "-" + monthNumber.toString() + "-" + dayNumber.toString());
    // return Duration.between(dateTime, LocalDateTime.now()).getSeconds();
    // Date date;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
      String dateInString = dayNumber.toString() + "-" + monthNumber.toString() + "-" + yearNumber.toString();//  "15-10-2015";
  
      Date date = sdf.parse(dateInString);
      System.out.println(date); //Prints Tue Oct 15 10:20:56 SGT 2015

      // convert date to seconds
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public static void fileDownload() {
    String lineName;
    long p1 = 0;
    long p2 = 0;
    String time;

    Scanner cruiseLine = new Scanner(System.in);
    Scanner p1test = new Scanner(System.in);
    Scanner p2test = new Scanner(System.in);
    
    Scanner monthNumberInput = new Scanner(System.in);
    Integer monthNumber = 0;

    Scanner dayNumberInput = new Scanner(System.in);
    Integer dayNumber = 0;

    Scanner yearNumberInput = new Scanner(System.in);
    Integer yearNumber = 0;
    
    Scanner frequencyInput = new Scanner(System.in);

    Scanner userInput2 = new Scanner(System.in);

    System.out.print("Enter the acronym for the line:\n");
    lineName = cruiseLine.next();
    System.out.println();
/*
    System.out.print("Enter p1:\n");
    p1 = p1test.nextLong();

    System.out.print("Enter p2:\n");
    p2 = p2test.nextLong();
*/
    
    for(int i = 0; i <= 1; i++) {
      if(i == 0) {
        System.out.print("Enter the start date\n");
        
      }
      if(i == 1) {
        System.out.print("Enter the end date:\n");
      }
      System.out.print("Month number:\n");
      monthNumber = monthNumberInput.nextInt();
      System.out.println();
      
      System.out.print("Day number:\n");
      dayNumber = dayNumberInput.nextInt();
      System.out.println();
  
      System.out.print("Year number:\n");
      yearNumber = yearNumberInput.nextInt();
      System.out.println();
      
      if(i == 0) {
        computeDateSeconds(yearNumber, monthNumber, dayNumber);
        // System.out.print(p1);
        
      }
      if(i == 1) {
        computeDateSeconds(yearNumber, monthNumber, dayNumber);
        // System.out.print(p2);
      }
      

      
    }
    
    System.out.print("Input frequency type: (wk/d/mo)\n");
    time = frequencyInput.next();
    if(time.equals("Month") || time.equals("Mo") || time.equals("month")) {
        time = "mo";
    }
    if(time.equals("Day") || time.equals("D") || time.equals("day")) {
      time = "d";
    }
    if(time.equals("Week") || time.equals("Wk") || time.equals("week")) {
      time = "wk";
    }
    // https://query1.finance.yahoo.com/v7/finance/download/RCL?period1=-86400&period2=1619308800&interval=1mo&events=history&includeAdjustedClose=true
    String url ="https://query1.finance.yahoo.com/v7/finance/download/" + lineName + "?period1=" + p1 + "&period2=" + p2 + "&interval=1" + time + "&events=history&includeAdjustedClose=true";
    System.out.println(url);
    try {
      BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream() ); 
      System.out.print("Input File Name:\n");
      outputName = userInput2.next();
      System.out.println();
      FileOutputStream fileOS = new FileOutputStream("C:/Users/Heather/Documents/GitHub/Web-Scrapers/dellprojectscraper/Output/" + outputName);
      byte data[] = new byte[1024];
      int byteContent;
      while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
        fileOS.write(data, 0, byteContent);
      }
      fileOS.close();
      cruiseLine.close();
      monthNumberInput.close();
      dayNumberInput.close();
      yearNumberInput.close();
      frequencyInput.close();
      userInput2.close();
      System.out.print("File Downloaded\n\n");
      // runLoop();
    } catch (IOException e) {
    }
  }


   public static void runLoop() {

    Scanner runFlag = new Scanner(System.in);
    String runCheck;
    // prompt to run
    System.out.print("Run (Y/N)\n");
    runCheck = runFlag.next();
    while (runCheck.equals("y") || runCheck.equals("Y")) {
        // get data from user
        // do stuff
        fileDownload();
        // rerun prompt
        System.out.print("Run again? (Y/N)\n");
        runCheck = runFlag.next();
    }
    System.out.print("Quitting...");
    

    // just ask for query parameters so we can make the download link

    
    // 45 years ago? 1420070400/(60 * 60 * 24 * 365) = 45.0301369863
    // Minutes * Hours * day * Year
    // some starting time to 1420070400
    // first day is dec 31 1969
    // 1420156800    Scanner userInput = new Scanner(System.in);
    
    //get date as day month year
    //convert the 3 numbers to seconds with java(java's system might not compute dates exactly like yahoo, but it's worth trying)
    
    
    // System.out.print("Run again? (Y/N)\n");
    // runCheck = runFlag.next();
    // prints false
    // System.out.println(runCheck.equals("y"));
    // if(runCheck == "y") {
    //   System.out.print("yes");
    // }
    
    // if(runCheck.equals("y") || runCheck.equals("Y") || runCheck.equals("yes") || runCheck.equals("Yes")) {
    //     System.out.println();
    //     System.out.println();
    //     // run the user entry code again in a new function stack frame
    //     // Recursion is a term used to call a function within a function
    //     fileDownload();
    // }else {
    //     System.out.print("Quitting...");
    // }
    
    runFlag.close();
  }
}

