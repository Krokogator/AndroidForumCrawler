package PartTwo_Parser;

public class Comment {
    private String id;
    private String iid;
    private String content;
    private String title;
    private String duration_start;
    private String duration_end;
    private String url;
    private String creator;
    private String lang;
    private String lname;

    public Comment(String url, String id, String iid, String creator, String content, String title, String duration){
        this.id = id;
        this.iid = iid;
        this.content = content;
        this.title = title;
        this.duration_start = duration;
        this.duration_end = duration;
        this.url = url;
        this.creator = creator;
        this.lang = "pl";
        this.lname = "Forum Android";
    }


    public String getId() {
        return id;
    }

    public String getIid() {
        return iid;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration_start() {
        return duration_start;
    }

    public String getDuration_end() {
        return duration_end;
    }

    public String getUrl() {
        return url;
    }

    public String getCreator() {
        return creator;
    }

    public String getLang() {
        return lang;
    }

    public String getLname() {
        return lname;
    }
}
