package model;

/**
 * The class represents the operations for image.
 */
public class Image extends AbstractImage {
  double width;
  double length;
  Pixel[][] pixels;

  /**
   * Constructor for Image.
   *
   * @param width  width of the board.
   * @param length length of the board.
   * @param pixel  pixel array that represents the image.
   */
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
  public AbstractImage blur(double[][] filter) {
    helpFilterOperate(filter);
    return this;
  }

  @Override
  public AbstractImage sharpen(double[][] filter) {
    helpFilterOperate(filter);
    return this;
  }

  @Override
  public AbstractImage greyScale(double[][] filter) {
    helpTransformOperate(filter);
    return this;
  }

  @Override
  public AbstractImage sepicaTone(double[][] filter) {
    helpTransformOperate(filter);
    return this;
  }

  @Override
  AbstractImage dither() {
    return null;
  }

  @Override
  AbstractImage mosaic() {
    return null;
  }

  private void helpTransformOperate(double[][] filter) {
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        int r = (int) (filter[0][0] * pixels[i][j].getR() + filter[0][1] * pixels[i][j].getG()
                + filter[0][2] * pixels[i][j].getB());
        int g = (int) (filter[1][0] * pixels[i][j].getR() + filter[1][1] * pixels[i][j].getG()
                + filter[1][2] * pixels[i][j].getB());
        int b = (int) (filter[2][0] * pixels[i][j].getR() + filter[2][1] * pixels[i][j].getG()
                + filter[2][2] * pixels[i][j].getB());
        pixels[i][j].setR(r);
        pixels[i][j].setG(g);
        pixels[i][j].setB(b);
      }
    }
    clamp();
  }

  private void helpFilterOperate(double[][] filter) {
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        pixels[i][j].setR(getFixedValue(filter, pixels, i, j, 0));
        pixels[i][j].setG(getFixedValue(filter, pixels, i, j, 1));
        pixels[i][j].setB(getFixedValue(filter, pixels, i, j, 2));
      }
    }
    clamp();
  }

  private int getFixedValue(double[][] filter, Pixel[][] pixels, int i, int j, int flag) {
    int len = filter.length;
    int width = filter[0].length;
    int res = 0;
    for (int x = i - (len - 1) / 2; x <= i + (len - 1) / 2; x++) {
      for (int y = j - (width - 1) / 2; y <= j + (width - 1) / 2; y++) {
        if (x >= 0 && x <= pixels.length - 1 && y >= 0 && y <= pixels[0].length - 1) {
          res += (flag == 0 ? pixels[x][y].getR() : flag == 1
                  ? pixels[x][y].getG() : pixels[x][y].getB())
                  * filter[x - i + (len - 1) / 2][y - j + (width - 1) / 2];
        }
      }
    }
    return res;
  }

  private void clamp() {
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        pixels[i][j].setR(fix(pixels[i][j].getR(), 0, 255));
        pixels[i][j].setG(fix(pixels[i][j].getG(), 0, 255));
        pixels[i][j].setB(fix(pixels[i][j].getB(), 0, 255));
      }
    }
  }

  private int fix(int x, int minimum, int maximum) {
    if (x < minimum) {
      return minimum;
    }
    return Math.min(maximum, x);
  }
}
