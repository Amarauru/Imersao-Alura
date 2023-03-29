import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer conexão http e as séries de TV mais populares

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();        
        List <Map<String, String>> listaDeSeries =  parser.parse (body);

        //exibir e manipular os dados
        
        for (Map<String,String> series : listaDeSeries)
         {
            System.out.println("\u001b[1mTítulo:\u001b[m "+ series.get ("title"));
            System.out.println("\u001b[3mPoster:\u001b[m" + series.get ("image"));
            System.out.println("\u001b[37m \u001b[41m Nota:\u001b[m"+ series.get ("imDbRating"));
            double classificacao = Double.parseDouble(series.get ("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            


            for (int n = 1; n <=numeroEstrelinhas; n++) {
                System.out.print
                ("⭐");
            }
            System.out.println("\n");
            
        }

    }
}
