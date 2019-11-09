package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class FileUtils {

  public static Pixel[][] getImagePixel(String image) {
    int[] rgb = new int[3];
    File file = new File(image);
    BufferedImage bi = null;
    try {
      bi = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    int width = bi.getWidth();
    int height = bi.getHeight();
    Pixel[][] data = new Pixel[height][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int pixel = bi.getRGB(i, j);
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        data[j][i] = new Pixel(rgb[0], rgb[1], rgb[2]);
      }
    }
    return data;
  }

  public static void output(Pixel[][] pixels, String path) throws IOException {

    int width = pixels[0].length;
    int height = pixels.length;
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Pixel pixel = pixels[j][i];
        int rgb = pixel.getR() * 256 * 256 + pixel.getG() * 256 + pixel.getB();
        bi.setRGB(i, j, rgb);
      }
    }

    Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
    ImageWriter writer = it.next();
    File f = new File(path);
    ImageOutputStream ios = ImageIO.createImageOutputStream(f);
    writer.setOutput(ios);
    writer.write(bi);
    bi.flush();
    ios.flush();
    ios.close();
  }

}
