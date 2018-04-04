package PartTwo_Parser;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    /** Druga czesc skryptu
     *  args[0] - folder z pobranymi plikami .html
     */



    public static void main(String args[]){
        FileObtainer ob = new FileObtainer();

        HTMLParser parser = new HTMLParser();

        List<Comment> comments = new ArrayList<>();

        for(Document f : ob.listFilesForFolder(args[0]))
        {
            comments.addAll(parser.parseComments(f));
        }

        Solr solr = new Solr();
        solr.commit(comments);

        /*for(Comment c : comments){
            System.out.println("id: "+c.getId());
            System.out.println("iid: "+c.getIid());
            System.out.println("content: "+c.getContent());
            System.out.println("title: "+c.getTitle());
            System.out.println("duration_start: "+c.getDuration_start());
            System.out.println("duration_end: "+c.getDuration_end());
            System.out.println("url: "+c.getUrl());
            System.out.println("creator: "+c.getCreator());
            System.out.println("lang: "+c.getLang());
            System.out.println("lname: "+c.getLname());
        }*/
    }



}
