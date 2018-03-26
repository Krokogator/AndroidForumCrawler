import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlackWidow {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    public List<String> crawl(String URL, int i){
        List<String> topicURLs = new ArrayList<>();
        List<String> sectionsURL = new ArrayList<>();
        String nextPageURL;
        String nextTopicPageURL;

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
                    topicURLs.add(regexFinder(text, "http://forum\\.android\\.com\\.pl/topic\\/(\\S)*?/"));
                } catch (Exception e){ }
                //Get subsections from current section
                try{
                    sectionsURL.add(regexFinder(text, "http://forum\\.android\\.com\\.pl/forum\\/(\\S)*?/"));
                } catch (Exception e){ }
                try{
                    nextPageURL = regexFinder(htmlDocument.getElementsByClass("ipsPagination_next").html(), "http://forum\\.android\\.com\\.pl/forum/.*page=\\d+");
                } catch (Exception e){ }
                try{
                    nextPageURL = regexFinder(htmlDocument.getElementsByClass("ipsPagination_next").html(), "http://forum\\.android\\.com\\.pl/topic/.*page=\\d+");
                } catch (Exception e){ }

            }

            //Get topics from last page
            if(!nextPageURL.equals("")){
                topicURLs.addAll(crawl(nextPageURL, i));
            }

            //Get topics from subsections
            for (String sectionURL:sectionsURL) {
                if(!sectionsURL.equals("")){
                    topicURLs.addAll(crawl(sectionURL, i+1));
                }
            }

            if(topicURLs.isEmpty()){
                System.out.println(regexFinder(htmlDocument.getElementsByClass("ipsPagination_last").html(), "http://forum\\.android\\.com\\.pl/topic/.*page=\\d+"));
            }
        }
        catch (Exception e){ }
        return topicURLs;
    }

    private String regexFinder(String html, String pattern) throws Exception {
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(html);
        if (m.find()) {
            return m.group(0);
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
}
