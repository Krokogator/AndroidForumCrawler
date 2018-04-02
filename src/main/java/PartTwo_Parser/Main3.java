package PartTwo_Parser;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.logging.Logger;

public class Main3 {
    private static final Logger LOGGER = Logger.getLogger(SolrClient.class.getName());

    private static final String SERVER_URL = "http://150.254.78.133:8983/solr/isi";

    public static void main(String[] args) throws IOException, SolrServerException {

        SolrClient solr = new HttpSolrClient.Builder(SERVER_URL).build();

        //QueryResponse response = solr.query(new SolrQuery("*:*"));


        SolrInputDocument doc = new SolrInputDocument();


        String id ="40731246293550973-1";
        String iid ="40731246293550973";
        String content="Też mi się tak wydaje. Mogła to być ludzka pomyłka, bo czegokolwiek bym nie robił, to forumowy skrypt sam z siebie nie wstawia mi cytatów niezależnie od mej woli";
        String title ="Regulamin ?";
        String duration_start ="2012-12-03T06:57:41Z";
        String duration_end="2012-12-03T06:57:41Z";
        String url="http://forum.android.com.pl/topic/77858-regulamin/?do=findComment&comment=1530167";
        String creator="Smogg";
        String lang="pl";
        String lname="Forum android.com.pl";

        doc.addField("id", id);
        doc.addField("iid",iid);
        doc.addField("content", content);
        doc.addField("title", title);
        doc.addField("duration_start", duration_start);
        doc.addField("duration_end", duration_end);
        doc.addField("url", url);
        doc.addField("creator", creator);
        doc.addField("lang", lang);
        doc.addField("lname", lname);

        UpdateResponse resp = solr.add(doc);
        solr.commit();

    }
}
