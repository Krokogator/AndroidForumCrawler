package PartTwo_Parser;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Solr {
    private static final Logger LOGGER = Logger.getLogger(SolrClient.class.getName());

    private static final String SERVER_URL = "http://150.254.78.133:8983/solr/isi";

    public void commit(List<Comment> comments){
        SolrClient solr = new HttpSolrClient.Builder(SERVER_URL).build();

        //QueryResponse response = solr.query(new SolrQuery("*:*"));



        UpdateResponse response;



        for(Comment c:comments){
            System.out.println(c.getId());
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", c.getId());
            doc.addField("iid",c.getIid());
            doc.addField("content", c.getContent());
            doc.addField("title", c.getTitle());
            doc.addField("duration_start", c.getDuration_start());
            doc.addField("duration_end", c.getDuration_end());
            doc.addField("url", c.getUrl());
            doc.addField("creator", c.getCreator());
            doc.addField("lang", c.getLang());
            doc.addField("lname", c.getLname());

            try {
                response = solr.add(doc);


            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            solr.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
