package tfip.ssf.day19.Model;

    import java.io.Serializable;

import jakarta.json.JsonObject;

    public class News implements Serializable{
        private String author;
        private String title;
        private String description;
        private String urlToImage;
        
        //Constructors
        public News(String author, String title, String description, String urlToImage) {
            this.author = author;
            this.title = title;
            this.description = description;
            this.urlToImage = urlToImage;
        }
        public News() {
        }

        //Methods
        public void create(JsonObject json) {
            if (!json.isNull("author")){
                this.setAuthor(json.getString("author"));
            }
            if (!json.isNull("title")){
                this.setTitle(json.getString("title"));
            }
            if (!json.isNull("description")){
                this.setDescription(json.getString("description"));
            }
            if (!json.isNull("urlToImage")){
                this.setUrlToImage(json.getString("urlToImage"));
            }
        }

        //Getters & Setters
        public String getAuthor() {
            return author;
        }
        public void setAuthor(String author) {
            this.author = author;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getUrlToImage() {
            return urlToImage;
        }
        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }
        
    }
