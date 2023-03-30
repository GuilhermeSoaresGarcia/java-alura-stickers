public class ContentImDb extends Content{
    private final Double rating;

    public ContentImDb(String title, String url, Double rating) {
        super(title, url);
        this.rating = rating;        
    }

    public Double getRating() {
        return rating;
    }

    // @Override
    // public String toString(){
    //     return super.toString() + "\n" + "Nota: " + rating;
    // }
    
}
