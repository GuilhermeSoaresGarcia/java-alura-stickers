import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImDbContentExtractor {

    public List<ContentImDb> contentExtractor(String body) {
        // Selecionar/"parsear" todos os dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(body);

        // com os dados parseados, cria um novo array, que receberá os dados que
        // interessam neste caso (Título, poster, nota/classificação)
        List<ContentImDb> listOfContents = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {
            String title = attribute.get("title");
            // string.replaceAll("[#%&{}\\<>*?/ $!'\":@+`|=]", "-")
            String imageUrl = attribute.get("image");
            Double contentRating = Double.parseDouble(attribute.get("imDbRating"));
            ContentImDb content = new ContentImDb(title, imageUrl, contentRating);
            listOfContents.add(content);
        }

        return listOfContents;

    }

}
