import java.util.Optional;

public class Content {
    // atributos da classe
    private final String title;
    private final String urlImage;
    private final int rating;
    
    
    // constructor
    public Content(String title, String urlImage, int rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
    }


    // getters
    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }    

    // FONTE: https://levelup.gitconnected.com/java-optional-is-not-so-obvious-263d9559dd41
    public Optional<Integer> getRating() {
        return Optional.ofNullable(rating);
    }    
}
