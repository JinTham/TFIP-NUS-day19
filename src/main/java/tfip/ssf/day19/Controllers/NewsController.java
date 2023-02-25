package tfip.ssf.day19.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfip.ssf.day19.Model.NewsList;
import tfip.ssf.day19.Services.NewsService;

@Controller
@RequestMapping(path="/news")
public class NewsController {
    @Autowired
    private NewsService newsSvc;
    
    @GetMapping
    public String getMapNews(Model model, @RequestParam String country, String keyword) {
        Optional<NewsList> opt = newsSvc.getNews(country, keyword);
        NewsList newsList = opt.get();
        model.addAttribute("newsList", newsList);
        return "newsPage";
    }

}
