import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Pegar os dados da API
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm"; // API do ImDb
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        // imdb está off... Por isso estou usando o mock acima
        
        // Documentação do HttpRequest: https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Selecionar/"parsear" os dados que me interessam (Título, poster, nota/classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size()); // .size é o método pra ver o comprimento da lista
        // System.out.println(listaDeFilmes.get(2)); // .get(índice) para pegar o item naquela posição da lista

        // // Exibir e manipular os dados
        // for (Map<String, String> movie : listaDeFilmes) {
        // // Para troca de cores: https://stackoverflow.com/a/45444716/18172843
        //     System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +
        //     ConsoleColors.GREEN_BACKGROUND + movie.get("title") + ConsoleColors.RESET);
        //     System.out.println(ConsoleColors.BLUE_UNDERLINED + movie.get("image") + ConsoleColors.RESET);
        //     System.out.println("Nota:" + " " + movie.get("imDbRating"));
        //     Double movieRating = Double.parseDouble(movie.get("imDbRating"));
        //     var roundedMovieRating = Math.floor(movieRating);
        //     String stars = "";
        //     for (int i = 0; i < roundedMovieRating; i++) {
        //     stars += "⭐";
        //     }
        //     System.out.println(stars);
        //     System.out.println();
        // }

        // criar os stickers com os cartazes dos filmes
        StickersGenerator stickersGenerator = new StickersGenerator();
        for (Map<String, String> movie : listaDeFilmes) {
            Double movieRating = Double.parseDouble(movie.get("imDbRating"));
            int roundedMovieRating = (int)Math.floor(movieRating);
            System.out.println(roundedMovieRating);
            String imageUrl = movie.get("image");
            String string = movie.get("title");
            String newOutputName = string.replaceAll("[#%&{}\\<>*?/ $!'\":@+`|=]", "-");
            InputStream image = new URL(imageUrl).openStream();
            stickersGenerator.createSticker(image, newOutputName, roundedMovieRating);
        }
    }
}
