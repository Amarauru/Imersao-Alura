
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.imageio.ImageIO;


public class GeradorDeStickers {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        //InputStream InputStream = new FileInputStream (new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_3.jpg").openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        //copiar a imagem original p/ nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0, 0, null);


        //configurar fonte
        var fonte = new Font("Impact", Font.BOLD, 100);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        String texto = "OPA,TESTE"; 

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posiçãoTextoX = (largura - larguraTexto) / 2;
        int posiçãoTextoY = novaAltura - 100;
        graphics.drawString(texto,posiçãoTextoX, posiçãoTextoY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, fontRenderContext);
        
        Shape outline =  textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posiçãoTextoX, posiçãoTextoY);
        graphics.setTransform(transform);

        var outlineStroke =new BasicStroke(largura *  0.004f);
        graphics.setStroke(outlineStroke); 

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/ " + nomeArquivo));


    }
    
}
