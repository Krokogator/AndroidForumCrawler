import java.util.List;

public class Main {

    //private static String website = "http://forum.android.com.pl/";
    private static String website = "http://forum.android.com.pl/forum/2154-pozosta%C5%82e-modele-z-serii-samsung-galaxy-s6-edge/";

    public static void main(String[] args) {
        BlackWidow widow = new BlackWidow();
        List<String> pages = widow.crawl(args[0], 0);

        System.out.println("\n\n--------------------------------------------RESULTS----------------------------------------------------\n\n");

        for(String page : pages){
            System.out.println(page);
        }
    }
}
