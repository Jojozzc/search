public class WebPage {
    // 标题
    private String title;

    // 链接
    private String url;

    // 简介
    private String summary;

    // 正文
    private String content;

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebPage buildUrl(String url){
        this.url = url;
        return this;
    }
    public WebPage buildTitle(String title){
        this.title = title;
        return this;
    }
    public WebPage buildContent(String content){
        this.content = content;
        return this;
    }
    public WebPage buildSummary(String summary){
        this.summary = summary;
        return this;
    }
}
