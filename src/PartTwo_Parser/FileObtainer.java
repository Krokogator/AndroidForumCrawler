package PartTwo_Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileObtainer {
    public List<Document> listFilesForFolder(String folderPath) {
        List<Document> files = new ArrayList<>();
        File folder = new File(folderPath);

        for (File fileEntry : folder.listFiles()) {
            files.add(FileToHTMLDoc(fileEntry));
        }
        return files;
    }

    private Document FileToHTMLDoc(File f){
        try {
            return Jsoup.parse(f, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
