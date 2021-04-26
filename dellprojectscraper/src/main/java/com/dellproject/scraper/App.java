
package com.dellproject.scraper;

import java.io.IOException;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sputnikdev.bluetooth.URL;

// import net.bytepowered.common.lang.HashMap;
// import okhttp3.benchmarks.UrlConnection;

// import BeautifulSoup
// I'm after steven J after about an hr
// https://www.codegrepper.com/code-examples/python/how+to+scrape+.ashx
public class App {

  public static void main(String[] args) {
    System.out.println( "Hello World!" );
    try {
      HashMap<String,String> variable = new HashMap<String, String>();
      variable.put("type", "application/x-google-chrome-pdf");
        // Here we create a document object and use JSoup to fetch the website
      Document doc = Jsoup.connect("https://www.cruisemapper.com/wiki/759-how-much-does-a-cruise-ship-cost").get();
      Elements repositories = doc.getElementsByClass("table table-striped");
      Elements stuff = doc.select("th[style]");
       //("height: 62px;");
      // System.out.println(repositories);
      System.out.println(stuff);

      /*
      header: 
        th style= height: 62px;

      body rows
        td style= height: 36px;
        6 items per row
      our table data structure:

      {
        'header': [stuff],
        'rows' : [stuff]
      }

      information importance:
      ship name
      cost to build
      year built

      */


      // don't know how to scrape this page

      // attempt 1
      // Document doc2 = Jsoup.connect("https://cruising.org/-/media/research-updates/research/2021-state-of-the-cruise-industry_optimized.ashx").ignoreContentType(true).get();
      // System.out.println(doc2);


      // attempt 2
      // // Initialize UnSupportedMimeTypeExeception class 
      // UnsupportedMimeTypeException mimeType = new UnsupportedMimeTypeException("Hey this is Mime",
      //     "application/x-google-chrome-pdf",
      //     "https://cruising.org/-/media/research-updates/research/2021-state-of-the-cruise-industry_optimized.ashx");
      // String mime = mimeType.getMimeType();

      // Document test = Jsoup.connect("https://cruising.org/-/media/research-updates/research/2021-state-of-the-cruise-industry_optimized.ashx")
      // // .requestBody("JSON")
      // .header("Content-Type", mime)
      // // .cookies(response.cookies())
      // .ignoreContentType(true)
      // .get();
      // System.out.println(test);


      // attempt 3 (doesn't compile)
      // URL url = 
      // new URL( "https://github.com/NITW-Group-B/Web-Scrapers/blob/david-branch/2021%20State%20of%20the%20Cruise%20Industry_optimized.pdf" );

      // UrlConnection connection = url.openConnection();

      // input = connection.getInputStream();

      // Document doc3 = Jsoup.parse(input, "UTF-8");
      // System.out.println(doc3.toString());

      // With the document fetched, we use JSoup's title() method to fetch the title
      // System.out.printf("Title: %s\n", doc.title());

      // Get the list of repositories
      // Elements repositories = doc.getElementsByClass("repo-item");

      /**
       * For each repository, extract the following information:
       * 1. Title
       * 2. Number of issues
       * 3. Description
       * 4. Full name on github
       */
      // for (Element repository : repositories) {
      //   // Extract the title
      //   String repositoryTitle = repository.getElementsByClass("repo-item-title").text();

      //   // Extract the number of issues on the repository
      //   String repositoryIssues = repository.getElementsByClass("repo-item-issues").text();

      //   // Extract the description of the repository
      //   String repositoryDescription = repository.getElementsByClass("repo-item-description").text();

      //   // Get the full name of the repository
      //   String repositoryGithubName = repository.getElementsByClass("repo-item-full-name").text();

      //   // The reposiory full name contains brackets that we remove first before generating the valid Github link.
      //   String repositoryGithubLink = "https://github.com/" + repositoryGithubName.replaceAll("[()]", "");

      //   // Format and print the information to the console
      //   System.out.println(repositoryTitle + " - " + repositoryIssues);
      //   System.out.println("\t" + repositoryDescription);
      //   System.out.println("\t" + repositoryGithubLink);
      //   System.out.println("\n");
      // }
 
      // In case of any IO errors, we want the messages written to the console
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
}
