package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.GreyScaleController;

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
  public AbstractImage dither() {
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        manipulate(i, j);
      }
    }
    return this;
  }

  private void manipulate(int i, int j) {
    int old_color = pixels[i][j].getR();
    int new_color = 255 - old_color > old_color ? 0 : 255;
    int err = old_color - new_color;
    pixels[i][j].setR(new_color);
    pixels[i][j].setG(new_color);
    pixels[i][j].setB(new_color);
    if (j + 1 <= pixels[0].length - 1) {
      int R = (int) (7.0 / 16 * err + pixels[i][j + 1].getR());
      R = Math.min(255, R);
      R = Math.max(0, R);
      pixels[i][j + 1].setR(R);
      pixels[i][j + 1].setG(R);
      pixels[i][j + 1].setB(R);
    }
    if (i + 1 <= pixels.length - 1 && j - 1 >= 0) {
      int R = (int) (3.0 / 16 * err + pixels[i + 1][j - 1].getR());
      R = Math.min(255, R);
      R = Math.max(0, R);
      pixels[i + 1][j - 1].setR(R);
      pixels[i + 1][j - 1].setG(R);
      pixels[i + 1][j - 1].setB(R);
    }
    if (i + 1 <= pixels.length - 1) {
      int R = (int) (5.0 / 16 * err + pixels[i + 1][j].getR());
      R = Math.min(255, R);
      R = Math.max(0, R);
      pixels[i + 1][j].setR(R);
      pixels[i + 1][j].setG(R);
      pixels[i + 1][j].setB(R);
    }
    if (i + 1 <= pixels.length - 1 && j + 1 <= pixels[0].length - 1) {
      int R = (int) (1.0 / 16 * err + pixels[i + 1][j + 1].getR());
      R = Math.min(255, R);
      R = Math.max(0, R);
      pixels[i + 1][j + 1].setR(R);
      pixels[i + 1][j + 1].setG(R);
      pixels[i + 1][j + 1].setB(R);
    }
  }

  @Override
  public AbstractImage mosaic(int seeds) {
    int[][] seed_index = new int[seeds][2];
    int length = pixels.length;
    int width = pixels[0].length;
    int[][] classify = new int[length][width];
    Random rd = new Random();
    for (int i = 1; i <= seeds; i++) {
      int x = rd.nextInt(pixels.length);
      int y = rd.nextInt(pixels[0].length);
      seed_index[i - 1] = new int[]{x, y};
    }
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        long min = Long.MAX_VALUE;
        for (int k = 0; k <= seed_index.length - 1; k++) {
          long dis = distance(i, j, seed_index[k][0], seed_index[k][1]);
          if (dis < min) {
            classify[i][j] = k;
            min = dis;
          }
        }
      }
    }
    List<List<int[]>> list = new ArrayList<>();
    for (int i = 0; i <= seeds - 1; i++) {
      list.add(new ArrayList<>());
    }
    for (int i = 0; i <= length - 1; i++) {
      for (int j = 0; j <= width - 1; j++) {
        list.get(classify[i][j]).add(new int[]{i, j});
      }
    }
    for (int i = 0; i <= seeds - 1; i++) {
      if (list.get(i).size() == 0) continue;
      int r = 0, g = 0, b = 0;
      for (int[] cur : list.get(i)) {
        r += pixels[cur[0]][cur[1]].getR();
        g += pixels[cur[0]][cur[1]].getG();
        b += pixels[cur[0]][cur[1]].getB();
      }
      r = r / list.get(i).size();
      g = g / list.get(i).size();
      b = b / list.get(i).size();
      for (int[] cur : list.get(i)) {
        pixels[cur[0]][cur[1]].setR(r);
        pixels[cur[0]][cur[1]].setG(g);
        pixels[cur[0]][cur[1]].setB(b);
      }
    }
    return this;
  }

  private long distance(int x1, int y1, int x2, int y2) {
    return (long) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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
