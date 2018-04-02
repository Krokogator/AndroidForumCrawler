import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    /**
     * PIERWSZA CZESC SKRYPTU
     *
     *  Pierwszy argument - link do forum/działu/tematu (dowolnie, pobierze wszystko wgłąb)
     *  Drugi argument - nazwa pliku wynikowego (txt)
     *
     *  Plikiem wynikowym są linki do stron tematów, które można szybciej pobrać korzystając z np. aria2c
     */

    public static void main(String[] args) {
        BlackWidow widow = new BlackWidow();

        try {
                List<String> subforum = widow.crawl(args[0], 0);
                SaveToFile.save(args[1], subforum);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
}
