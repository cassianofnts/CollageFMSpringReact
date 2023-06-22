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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateCollage(ArrayList<Album> listaDeAlbuns, String usuario, Integer limit) {
        var largura = 0;
        var altura = 0;

        int larguraMax = 0;
        int alturaMax;

        if(limit == 9)
            larguraMax = 900;
        else if(limit ==16)
            larguraMax = 1200;
        else
            larguraMax = 1500;

        alturaMax = larguraMax;

        BufferedImage collage = new BufferedImage(larguraMax, alturaMax, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = collage.createGraphics();
        graphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

        for(Album album : listaDeAlbuns){

            graphics.drawImage(geraImagemAlbum(album), largura, altura, null);
            largura += 300;
            
            if (largura == larguraMax){
                largura = 0;
                altura += 300;
            }
        }

        File arquivo = new File("../api/src/images", usuario+".png");
        arquivo.mkdir();

        try {
            ImageIO.write(collage, "png", arquivo);
            return arquivo.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
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
