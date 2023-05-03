package com.cassiano.collagefm.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.cassiano.collagefm.domain.Album;

public class Imagens {
    public static BufferedImage geraImagemAlbum(Album album){
        URI endereco = URI.create(album.getCoverImage());
        BufferedImage imgOriginal, imgNova;

        try {
            imgOriginal = ImageIO.read(endereco.toURL());
            imgNova = new BufferedImage(imgOriginal.getWidth(), imgOriginal.getHeight(), BufferedImage.TRANSLUCENT);

            Graphics2D graphics = imgNova.createGraphics();

            graphics.drawImage(imgOriginal, 0, 0, null);
            graphics.setFont(new Font("Arial", Font.PLAIN, 12));
            
            drawText(graphics, 10, 260, album.getArtistName());
            drawText(graphics, 10, 280, album.getAlbumName());

            return imgNova;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void generateCollage(ArrayList<Album> listaDeAlbuns, String usuario) {
        BufferedImage collage = new BufferedImage(1500, 1500, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = collage.createGraphics();
        var largura = 0;
        var altura = 0;

        for(Album album : listaDeAlbuns){

            graphics.drawImage(geraImagemAlbum(album), largura, altura, null);
            largura += 300;
            
            if (largura == 1500){
                largura = 0;
                altura += 300;
            }
        }

        File arquivo = new File("./api/src/images", usuario+".png");
        arquivo.mkdir();

        try {
            ImageIO.write(collage, "png", arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void drawText(Graphics2D graphics, int x, int y, String text){
       
        GlyphVector glyphVector = graphics.getFont().createGlyphVector(graphics.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform oldPosition = graphics.getTransform();

        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        graphics.translate(x, y);
        graphics.draw(textShape);
        graphics.setColor(Color.white);
        graphics.fill(textShape);

        graphics.setTransform(oldPosition);

    }

}
