package PartTwo_Parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    public List<Comment> parseComments(Document html){
        Elements rawComments = getCommentsRaw(html);

        List<Comment> fineComments = new ArrayList<>();
                //Title
        String title = getTitle(html);

        for(Element rawComment: rawComments) {

            //Content
            String content = getContent(rawComment);

            //Creator
            String creator = (getCreator(rawComment));

            //Date
            String date = getDate(rawComment);

            //Link
            String link = getLink(rawComment);

            //Iid
            String iid = MD5.calc(link);

            //Iid
            String id = iid + "-1";

            fineComments.add(new Comment(link, id, iid, creator, content, title, date));
        }
        return fineComments;
    }

    private Elements getCommentsRaw(Document html){
        return html.getElementsByTag("article");
    }

    private String getTitle(Document html) { return html.getElementsByAttributeValue("property", "og:title").first().attr("content"); }

    private String getContent(Element element){
        Elements e = element.getElementsByAttributeValue("data-role", "commentContent");
        Elements textRaw = e.first().getElementsByTag("p");
        StringBuilder sb = new StringBuilder();
        String out="";
        for(Element temp : textRaw){
            sb.append(temp.text()+"\n");
        }
        try{
            out = sb.substring(0, sb.length()-1);
        } catch (Exception ex){}


        return out;
    }

    private String getCreator(Element element){
        Elements elements = element.getElementsByAttributeValue("itemprop", "name");
        return elements.first().text();
    }

    private String getDate(Element element){
        return element.getElementsByAttributeValueMatching("datetime", "\\d+-\\d+-\\d+T\\d+:\\d+:\\d+Z").attr("datetime");
    }

    private String getLink(Element element){
        return element.getElementsByAttributeValue("class", "ipsType_reset").first().getElementsByClass("ipsType_blendLinks").first().attr("href");
    }


}
