package tfip.ssf.day19.Services;

import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import tfip.ssf.day19.Model.News;
import tfip.ssf.day19.Model.NewsList;

@Service
public class NewsService {
    @Value("${news.key}")
    private String apiKey;
    
    public Optional<NewsList> getNews(String country, String keyword) {
        //Send request entity to News API website
        //https://newsapi.org/v2/top-headlines?country={country}&keyword={keyword}&apiKey={apiKey}
        String url = UriComponentsBuilder.fromUriString("https://newsapi.org/v2/top-headlines")
                .queryParam("country",country)
                .queryParam("q",keyword)
                .queryParam("apiKey",apiKey)
                .toUriString();

        //System.out.printf("URL: %s\n", url);
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();

        //Receive response entity from News API website
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        String payload = "";
        int statusCode = 500;
        try {
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            statusCode = resp.getStatusCode().value();
        } catch (HttpClientErrorException ex) {
            payload = ex.getResponseBodyAsString();
            statusCode = ex.getStatusCode().value();
            return Optional.empty();
        } finally {
            //System.out.printf("Status code: %d\n", statusCode);
            //System.out.printf("Payload: %s\n", payload);
        }

        //Read the content from the JsonObject
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();

        //Parse the responsebody into a NewsList object
        NewsList newsList = new NewsList();
        newsList.setCountry(country);
        newsList.setStatus(json.getString("status"));
        newsList.setTotalResults(json.getInt("totalResults"));

        JsonArray articles = json.getJsonArray("articles");
        for (int i=0;i<articles.size();i++) {
            News news = new News();
            news.create(articles.getJsonObject(i));
            newsList.addNews(news);
        }

        return Optional.of(newsList);

    }
}
