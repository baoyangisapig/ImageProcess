package model;

public class Image extends AbstractImage {
  double width;
  double length;
  Pixel[][] pixels;

  public Image(double width, double length, Pixel[][] pixel) {
    this.width = width;
    this.length = length;
    this.pixels = pixel;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public Pixel[][] getPixels() {
    return pixels;
  }

  public void setPixels(Pixel[][] pixels) {
    this.pixels = pixels;
  }

  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  @Override
  AbstractImage blur(int[][] filter) {
    return null;
  }

  @Override
  AbstractImage sharpen(int[][] filter) {
    return null;
  }

  @Override
  AbstractImage greyScale(int[][] filter) {
    return null;
  }

  @Override
  AbstractImage sepicaTone(int[][] filter) {
    return null;
  }
}
