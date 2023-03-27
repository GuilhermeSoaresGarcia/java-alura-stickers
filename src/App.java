import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Pegar os dados da API 
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm"; // API do imdb está off... Por isso estou usando o mock acima
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Selecionar/"parsear" os dados que me interessam (Título, poster, nota/classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size()); // .size é o método pra ver o comprimento da lista
        // System.out.println(listaDeFilmes.get(2)); // .get(índice) para pegar o item naquela posição da lista

        // Exibir e manipular os dados
        // Para troca de cores: https://stackoverflow.com/a/45444716/18172843
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + filme.get("title") + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_UNDERLINED + filme.get("image") + ConsoleColors.RESET);
            System.out.println("Nota:" + " " + filme.get("imDbRating"));
            String ratingString = filme.get("imDbRating");
            int ratingNumber = Integer.parseInt(ratingString);
            System.out.println(ratingNumber);
            var bla1 = "";
            for (int x = 0; x < ratingNumber; x++) {
                bla1 += "@";
            }
            System.out.println(bla1);
            System.out.println("😀");
            System.out.println();
        }
    }
}
