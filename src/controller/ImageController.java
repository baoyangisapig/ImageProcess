package controller;

import java.io.IOException;

import model.FileUtils;
import model.Image;

/**
 * The controller for image processing.
 * @Author Yang Bao && YiMing Chu
 */
public class ImageController {
  public void blur(double[][] filter,String inputPath,String outputPath) throws IOException {
    Image image=new Image(400,400, FileUtils.getImagePixel(inputPath));
    Image image1=(Image) image.blur(filter);
    FileUtils.output(image1.getPixels(),outputPath);
  }

  public void sharpen(double[][] filter,String inputPath,String outputPath) throws IOException {
    Image image=new Image(400,400,FileUtils.getImagePixel(inputPath));
    Image image1=(Image) image.sharpen(filter);
    FileUtils.output(image1.getPixels(),outputPath);
  }

  public void greyScale(double[][] filter,String inputPath,String outputPath) throws IOException {
    Image image=new Image(400,400,FileUtils.getImagePixel(inputPath));
    Image image1=(Image) image.greyScale(filter);
    FileUtils.output(image1.getPixels(),outputPath);
  }

  public void sepicaTone(double[][] filter,String inputPath,String outputPath) throws IOException {
    Image image=new Image(400,400,FileUtils.getImagePixel(inputPath));
    Image image1=(Image) image.sepicaTone(filter);
    FileUtils.output(image1.getPixels(),outputPath);
  }


}
