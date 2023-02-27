package tfip.ssf.day19.Repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import tfip.ssf.day19.Model.NewsList;

@Repository
public class NewsRepo {
    
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void saveHist(String country, String keyword, NewsList newsList) {
        redisTemplate.opsForHash().put("searchHist", country+","+keyword, newsList);
    }

    public List<String> getSearchHist(){
        List<String> keys = new LinkedList<>();
        for (Object key : redisTemplate.opsForHash().keys("searchHist")) {
            keys.add((String) key);
        }
        return keys;
    }
}
