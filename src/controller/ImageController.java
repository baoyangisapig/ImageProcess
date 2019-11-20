package controller;

import java.io.IOException;

import model.FileUtils;
import model.Image;

/**
 * The controller for image processing.
 *
 * @Author Yang Bao && YiMing Chu
 */
public class ImageController {
  /**
   * Blur the image.
   *
   * @param filter     Filter to blur.
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @throws IOException Throw IOException.
   */
  public void blur(double[][] filter, String inputPath, String outputPath) throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.blur(filter);
    FileUtils.output(image1.getPixels(), outputPath);
  }

  /**
   * Sharpen the picture.
   *
   * @param filter     The filter for sharpening.
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @throws IOException Throw IOException.
   */
  public void sharpen(double[][] filter, String inputPath, String outputPath) throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.sharpen(filter);
    FileUtils.output(image1.getPixels(), outputPath);
  }

  /**
   * Grey Scale the picture.
   *
   * @param filter     Filter for grey scale.
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @throws IOException Throw IOException.
   */
  public void greyScale(double[][] filter, String inputPath, String outputPath) throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.greyScale(filter);
    FileUtils.output(image1.getPixels(), outputPath);
  }

  /**
   * SepicaTone the picture.
   *
   * @param filter     Filter for sepicaTone.
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @throws IOException Throw IOException.
   */
  public void sepicaTone(double[][] filter, String inputPath, String outputPath)
          throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.sepicaTone(filter);
    FileUtils.output(image1.getPixels(), outputPath);
  }

  /**
   * dither the picture.
   *
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @throws IOException Throw IOException.
   */
  public void dither(String inputPath, String outputPath) throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.dither();
    FileUtils.output(image1.getPixels(), outputPath);
  }

  /**
   * mosaic the input picture.
   *
   * @param inputPath  Input path of the picture.
   * @param outputPath Output path of the picture.
   * @param seeds      number of seeds.
   * @throws IOException Throw IOException.
   */
  public void mosaic(String inputPath, String outputPath, int seeds) throws IOException {
    Image image = new Image(400, 400, FileUtils.getImagePixel(inputPath));
    Image image1 = (Image) image.mosaic(seeds);
    FileUtils.output(image1.getPixels(), outputPath);
  }
}
