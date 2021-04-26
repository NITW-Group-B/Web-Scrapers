
package com.dellproject.scraper;

import java.io.IOException;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.bytepowered.common.lang.HashMap;

// import BeautifulSoup

// https://www.codegrepper.com/code-examples/python/how+to+scrape+.ashx
public class App {

  public static void main(String[] args) {
    System.out.println( "Hello World!" );
    try {
      HashMap<String,String> variable = new HashMap<String, String>();
      variable.put("type", "application/x-google-chrome-pdf");
        // Here we create a document object and use JSoup to fetch the website
      Document doc = Jsoup.connect("https://www.codetriage.com/?language=Java").get();
      Document doc2 = Jsoup.connect("https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/problem12.html").get();

        // don't know how to scrape this site

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
      System.out.println(doc2);

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
