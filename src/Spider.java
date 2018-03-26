import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {
    private static final int MAX_PAGES_TO_SEARCH = 50000;
    private Set<String> pagesVisited = new HashSet<String>();
    private Set<String> sectionVisited = new HashSet<String>();

    private List<String> pagesToVisit = new LinkedList<String>();
    private List<String> sectionsToVisit = new LinkedList<String>();

    public void search(String url) {
        while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if (this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.pagesVisited.add(url);
            } else {
                currentUrl = this.nextUrl();
            }

            //Get links from url and only this url
            leg.crawl(currentUrl);
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
    }


    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
}