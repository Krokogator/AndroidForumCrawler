package PartTwo_Parser;

import org.apache.solr.client.solrj.SolrClient;
import org.jsoup.nodes.Document;

import javax.swing.text.html.HTMLDocument;

public class Main2 {
    /**
     *
     */



    public static void main(String args[]){
        FileObtainer ob = new FileObtainer();

        HTMLParser parser = new HTMLParser();


        for(Document f : ob.listFilesForFolder("C:\\Users\\micha\\Desktop\\AndroidCrawler\\Downloaded\\subforum2"))
        {
            parser.parseComments(f);
        }
    }



}
