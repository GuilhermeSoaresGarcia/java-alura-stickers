public class Content {
    // atributos da classe
    private final String title;
    private final String urlImage;
    
    // constructor
    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }


    // getters
    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }    
}
