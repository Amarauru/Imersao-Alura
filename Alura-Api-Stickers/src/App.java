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
        
        // fazer conexão http e buscar filmes mais populares

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();        
        List <Map<String, String>> listaDeFilmes =  parser.parse (body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes)
         {
            String urlImagem = filme.get("image");
            String titulo = (filme.get ("title"));
            String nomeArquivo = titulo + " .png";

            InputStream inputStream = new URL(urlImagem).openStream();

            var gerador = new GeradorDeStickers();
            gerador.cria(inputStream, nomeArquivo);

            System.out.println(filme.get ("title"));
            System.out.println(filme.get ("image"));
            System.out.println(filme.get ("imDbRating"));
            System.out.println();
        }

    }
}
