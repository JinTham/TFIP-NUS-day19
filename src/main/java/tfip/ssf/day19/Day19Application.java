package tfip.ssf.day19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tfip.ssf.day19.Services.NewsService;

@SpringBootApplication
public class Day19Application implements CommandLineRunner{
	
	@Autowired
	private NewsService newsSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day19Application.class, args);
	}

	@Override
	public void run(String... args){
		newsSvc.getNews("SG","AI");
	}
}
