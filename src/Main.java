public class Main {

    //private static String website = "http://forum.android.com.pl/";
    private static String website = "http://forum.android.com.pl/forum/2154-pozosta%C5%82e-modele-z-serii-samsung-galaxy-s6-edge/";

    public static void main(String[] args) {
        BlackWidow widow = new BlackWidow();
        System.out.println(widow.crawl(args[0], 0).size());
    }
}
