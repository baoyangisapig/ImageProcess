package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * Util class to get image from path and convert it to Pixel array. It can also output cached Pixel
 * to image with specific path.
 */
public class FileUtils {

  /**
   * Get image from path and cache it in Pixel arrays.
   *
   * @param path Path of the image.
   * @return Pixel array caching information of the image.
   */
  public static Pixel[][] getImagePixel(String path) {

    // Get BufferedImage from the picture of the path.
    int[] rgb = new int[3];
    File file = new File(path);
    BufferedImage bi = null;
    try {
      bi = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    int width = bi.getWidth();
    int height = bi.getHeight();

    // Convert picture to pixel array.
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

  /**
   * Output stored image to specific path.
   *
   * @param pixels Stored pixel array.
   * @param path   Output path.
   * @throws IOException If there is an IOException, throw it.
   */
  public static void output(Pixel[][] pixels, String path) throws IOException {

    // Convert pixels to bufferedImage.
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

    // Draw picture to given path.
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
