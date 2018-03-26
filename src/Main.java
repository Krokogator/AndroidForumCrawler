public class Main {

    private static String website = "http://forum.android.com.pl/";

    public static void main(String[] args) {
        BlackWidow widow = new BlackWidow();
        widow.crawl(website, 0);
    }
}
