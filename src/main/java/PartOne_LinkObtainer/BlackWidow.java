package PartOne_LinkObtainer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlackWidow {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    public List<String> crawl(String URL, int i){
        List<String> topicsToVisit = new ArrayList<>();
        List<String> topicURLs = new ArrayList<>();
        List<String> sectionsURL = new ArrayList<>();
        String nextPageURL;
        String nextTopicPageURL;
        String lastPage = "1";

        System.out.println(getISpaces(i)+URL);

        try{
            Connection connection = Jsoup.connect(URL).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            Elements linksOnPage = htmlDocument.getElementsByClass("ipsDataItem_title");

            nextPageURL="";
            nextTopicPageURL="";

            for (Element link:linksOnPage) {
                String text = link.html();
                //Get topics from current section
                try {
                    topicsToVisit.add(regexFinder(text, "(http://forum\\.android\\.com\\.pl/topic\\/(\\S)*?/)"));
                } catch (Exception e){ }
                //Get subsections from current section
                try{
                    sectionsURL.add(regexFinder(text, "(http://forum\\.android\\.com\\.pl/forum\\/(\\S)*?/)"));
                } catch (Exception e){ }
                try{
                    if(!htmlDocument.getElementsByClass("ipsPagination_next").hasClass("ipsPagination_inactive")){
                        nextPageURL = regexFinder(htmlDocument.getElementsByClass("ipsPagination_next").html(), "(http://forum\\.android\\.com\\.pl/forum/.*page=\\d+)");
                    }
                } catch (Exception e){ }

            }

            try{
                lastPage = regexFinder(htmlDocument.toString(), "\"pageEnd\": (\\d+)");
                System.out.println(lastPage);
            } catch (Exception e){}

            boolean lastTopicPage = true;

            //If last subsection page/next page not present => crawl into topic
            for(String topic:topicsToVisit){
                //System.out.println("Crawl into topic, topics: "+topicURLs.size());
                topicURLs.addAll(crawl(topic, i+1));
                lastTopicPage = false;
            }

            //If subsections present => crawl into them
            if(nextPageURL.equals("")){
                for (String sectionURL:sectionsURL) {
                    //System.out.println("Crawl into section");
                    if(!sectionsURL.equals("")){
                        topicURLs.addAll(crawl(sectionURL, i+1));
                        lastTopicPage = false;
                    }
                }
            }

            //If subsections visited/not present => crawl into next subsection page
            if(!nextPageURL.equals("")){
                //System.out.println("Crawl into next section page");
                topicURLs.addAll(crawl(nextPageURL, i));
                lastTopicPage = false;
            }

            if(lastTopicPage==true){
                try{
                    for(int page = 1;page<=Integer.parseInt(lastPage);page++){
                        topicURLs.add(URL+"?page="+page);
                    }

                }catch (Exception e){ }
            }



        }
        catch (Exception e){

            System.out.println("Nieudane połączenie z: "+URL);
        }
        return topicURLs;
    }

    private String regexFinder(String html, String pattern) throws Exception {
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(html);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new Exception();
        }
    }

    private String getISpaces(int i){
        String out = "";
        for(int x=0; x<i;x++){
            out+="  ";
        }
        return out;
    }

    public List<String> getSubforums(String URL) throws IOException {
        List<String> subforums = new ArrayList<>();

        Connection connection = Jsoup.connect(URL).userAgent(USER_AGENT);
        Document htmlDocument = connection.get();
        Elements linksOnPage = htmlDocument.getElementsByClass("ipsDataItem_title");

        List<String> sectionsURL = new ArrayList<>();

        for(Element e : linksOnPage) {
            //Get subsections from current section
            try {
                String text = e.html();
                subforums.add(regexFinder(text, "(http://forum\\.android\\.com\\.pl/forum\\/(\\S)*?/)"));
            } catch (Exception ex) {
            }
        }
        return subforums;
    }
}
