import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickersGenerator {

    public void createSticker(InputStream input, String outputFile, int rating) throws Exception {
        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(input);

        // cria uma nova imagem em memória, com transparência e novo tamanho
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newHeight = originalHeight + 200;
        BufferedImage newImage = new BufferedImage(originalWidth, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova (na memória)
        Graphics2D resizedImage = (Graphics2D) newImage.getGraphics();
        resizedImage.drawImage(originalImage, 0, 0, null);

        // definir a fonte que será usada no texto
        Font font = new Font(Font.SERIF, Font.BOLD, 50);
        resizedImage.setFont(font);
        resizedImage.setColor(Color.YELLOW);

        // escrever uma frase de efeito na nova imagem
        String text = "";
        if (rating >= 8) {
            text = "TOPZERA!!";
        } else if (rating <= 6) {
            text = "RUIM DEMAIS!";
        } else {
            text = "MARROMENO...";
        }

        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
        resizedImage.drawString(text, (originalWidth - textWidth) / 2, newHeight - 60);

        // escrever a imagem nova num arquivo
        File file = new File("src/output/");
        if (!file.exists()) {
            file.mkdir();
        }
        ImageIO.write(newImage, "png", new File("src/output/" + outputFile + ".png"));
        
    }

}
