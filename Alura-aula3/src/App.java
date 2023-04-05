import java.io.InputStream;
import java.net.URL;

import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer conexão http e buscar filmes mais populares

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        //exibir e manipular os dados
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        List <Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradorDeStickers();

        for (int i = 0; i< 3; i++) {
        
        Conteudo conteudo = conteudos.get (i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/"+ conteudo.getTitulo() + ".png";
            
            gerador.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
    
        }

    }
}
