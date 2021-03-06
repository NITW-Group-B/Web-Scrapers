
package com.dellproject.scraper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class App {


  public static String downloadLink;
  public static String outputName;

  public static int stopFlag = 0;
  public static boolean firstrun = true;
  public static String user;
  


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

  
      output.close();
      //output2.close();
      
      
      
      
      
       
      // In case of any IO errors, we want the messages written to the console
      } catch (IOException e) {
        e.printStackTrace();
      }

      //runLoop();
      fileDownload();
  
  }
  
  public static /*long*/String computeDateSeconds(Integer yearNumber, Integer monthNumber, Integer dayNumber) {
    
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-m-d");
    // LocalDateTime dateTime = DateTime.parse(yearNumber.toString() + "-" + monthNumber.toString() + "-" + dayNumber.toString());
    // return Duration.between(dateTime, LocalDateTime.now()).getSeconds();
    // Date date;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
      String dateInString = dayNumber.toString() + "-" + monthNumber.toString() + "-" + yearNumber.toString();//  "15-10-2015";
  
      Date date = sdf.parse(dateInString);
      String milliseconds = (new Long(date.getTime())).toString();
      // Long seconds = new Long(((long) milliseconds.doubleValue()) / 1000);

      //System.out.println("miliseconds: " + milliseconds); //Prints Tue Oct 15 10:20:56 SGT 2015
      // System.out.println("seconds: " + seconds.toString());
      String seconds = "";
      for(int i = 0; i < milliseconds.length() - 3; i++) {
        seconds = seconds + milliseconds.charAt(i);
      }
      return seconds;

      // convert date to seconds
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String milliseconds = "0";
    return milliseconds;
    
  }
  
  public static void fileDownload() {
    String lineName;
    String p1 = new String();
    String p2 = new String();
    String time;

    Scanner userPath = new Scanner(System.in);

    Scanner cruiseLine = new Scanner(System.in);
    
    Scanner monthNumberInput = new Scanner(System.in);
    Integer monthNumber = 0;

    Scanner dayNumberInput = new Scanner(System.in);
    Integer dayNumber = 0;

    Scanner yearNumberInput = new Scanner(System.in);
    Integer yearNumber = 0;
    
    Scanner frequencyInput = new Scanner(System.in);

    Scanner userInput2 = new Scanner(System.in);

    System.out.print("Enter the acronym for the company from Yahoo Query:\n");
    lineName = cruiseLine.next();
    System.out.println();
    
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
        p1 = computeDateSeconds(yearNumber, monthNumber, dayNumber);
        //System.out.println(p1);
        
      }
      if(i == 1) {
        p2 = computeDateSeconds(yearNumber, monthNumber, dayNumber);
        //System.out.println(p2);
      }
      

      
    }
    
    System.out.print("Input frequency type: (wk/d/mo)\n");
    time = frequencyInput.next();
    System.out.println();
    if(time.equals("Month") || time.equals("Mo") || time.equals("month")) {
        time = "mo";
    }
    if(time.equals("Day") || time.equals("D") || time.equals("day")) {
      time = "d";
    }
    if(time.equals("Week") || time.equals("Wk") || time.equals("week")) {
      time = "wk";
    }

    String url ="https://query1.finance.yahoo.com/v7/finance/download/" + lineName + "?period1=" + p1 + "&period2=" + p2 + "&interval=1" + time + "&events=history&includeAdjustedClose=true";
    try {
      BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream() ); 
      System.out.print("Input File Name (Saves as .csv):\n");
      outputName = userInput2.next();
      System.out.println();
      
    
      if(firstrun == true){
      System.out.print("Input the name of the User profile you're in:\n");
      user = userPath.next();
      System.out.println();
      File file = new File("C:/Users/" + user + "/Documents/DellWebScraper/");
      boolean bool = file.mkdir();
      if(bool){
         System.out.println("Directory created at: C:/Users/" + user + "/Documents/DellWebScraper/");
      }else{
         
      }
      try{
      Files.move(Paths.get("C:/Users/" + user + "/Documents/GitHub/Web-Scrapers/dellprojectscraper/CruiseMapperOutput.csv"), Paths.get("C:/Users/" + user + "/Documents/DellWebScraper/CruiseMapperOutput.csv"), StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
          System.out.println("Cannot move CruiseMapper.csv, look in the main folder of this program for it");
      }

    }
      FileOutputStream fileOS = new FileOutputStream("C:/Users/" + user + "/Documents/DellWebScraper/" + outputName + ".csv");
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
      userPath.close();
      inputStream.close();




      System.out.print("File " + outputName + " saved at C:/Users/" + user + "/Documents/DellWebScraper/" + outputName + ".csv\n\n");
      firstrun = false;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    //Look over this again at some point
   /* public static void runLoop() {

    Scanner runFlag = new Scanner(System.in);
    String runCheck;
    Scanner runFlag2 = new Scanner(System.in);
    // Scanner firstFlag = new Scanner(System.in);
    // String firstCheck;
    // prompt to run
    System.out.print("Run (Y/N)\n");
    runCheck = runFlag.next();
    // if (firstCheck.equals("y") || firstCheck.equals("Y")){
    //   runCheck = "y";
    // }
    while (runCheck.equals("y") || runCheck.equals("Y")) {
        runFlag.close();
        // get data from user
        // https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
        // do stuff
        fileDownload();
        // rerun prompt
        // runFlag
        
        // System.out.print(runCheck);
        // System.out.print(runFlag);
        // runFlag = new Scanner(System.in);
        
        System.out.print("Run again? (Y/N)\n");
        System.out.print(runFlag2.hasNext());
        runCheck = runFlag2.next();
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
    runFlag2.close();
  } */

}



