import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=b1k7X16CTtIUkaZ0Klph90aI6aGviD2VHzIZEF6K&start_date=2022-06-12&end_date=2022-06-14";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        var data = new BodyRequester();
        String json = data.dataGetter(url);
        var parsedData = new ImDbContentExtractor();
        List<ContentImDb> listOfContent = parsedData.contentExtractor(json);

        // Exibir e manipular os dados
        for (ContentImDb content : listOfContent) {
            String title = ConsoleColors.WHITE_BOLD_BRIGHT + ConsoleColors.GREEN_BACKGROUND + content.getTitle() + ConsoleColors.RESET;
            String imageUrl = ConsoleColors.BLUE_UNDERLINED + content.getUrlImage() + ConsoleColors.RESET;
            String rate = "Nota:" + " " + content.getRating();
            String stars = "";
            for (int i = 0; i < content.getRating(); i++) {
                stars += "⭐";
            }
            System.out.println(title + "\n" + imageUrl + "\n" + rate + "\n" + stars + "\n");
        }

        // // criar os stickers com os cartazes dos filmes
        StickersGenerator stickersGenerator = new StickersGenerator();
        for (ContentImDb content : listOfContent) {
            String imageUrl = content.getUrlImage();
            InputStream image = new URL(imageUrl).openStream();
            String title = content.getTitle().replaceAll("[#%&{}\\<>*?/ $!'\":@+`|=]", "-");
            int roundedContentRating = (int)Math.floor(content.getRating());
            stickersGenerator.createSticker(image, title, roundedContentRating);
        }
    }
}
