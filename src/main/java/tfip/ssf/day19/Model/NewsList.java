package tfip.ssf.day19.Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class NewsList implements Serializable {
    private List<News> newsList = new LinkedList<>();
    private String status;
    private Integer totalResults;
    private String country;
    
    //Constructors
    public NewsList() {
    }

    //Methods
    public void addNews(News news) {
        this.newsList.add(news);
    }

    //Getters & Setters
    public List<News> getNewsList() {
        return newsList;
    }
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }   
    
}
