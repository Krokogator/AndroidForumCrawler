import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SaveToFile {
    public static void save(String filename, List<String> data) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(filename+".txt");
        for(String s : data){
            out.println(s);
        }
        out.close();
    }
}
