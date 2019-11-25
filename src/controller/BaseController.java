package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AbstractImage;
import model.Image;
import model.Pixel;

public class BaseController {
  public Pixel[][] clamp(Pixel[][] pixels) {
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        pixels[i][j].setR(fix(pixels[i][j].getR(), 0, 255));
        pixels[i][j].setG(fix(pixels[i][j].getG(), 0, 255));
        pixels[i][j].setB(fix(pixels[i][j].getB(), 0, 255));
      }
    }
    return pixels;
  }

  private int fix(int x, int minimum, int maximum) {
    if (x < minimum) {
      return minimum;
    }
    return Math.min(maximum, x);
  }

  protected Image helpFilterOperate(AbstractImage image, double[][] filter) {
    Image img = (Image) image;
    Pixel[][] pixels = img.getPixels();
    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        pixels[i][j].setR(getFixedValue(filter, pixels, i, j, 0));
        pixels[i][j].setG(getFixedValue(filter, pixels, i, j, 1));
        pixels[i][j].setB(getFixedValue(filter, pixels, i, j, 2));
      }
    }
    clamp(pixels);
    img.setPixels(pixels);
    return img;
  }

  protected AbstractImage helpTransformOperate(AbstractImage image, double[][] filter) {
    Image img = (Image) image;
    Pixel[][] pixels = img.getPixels();
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
    clamp(pixels);
    img.setPixels(pixels);
    return img;
  }

  protected int getFixedValue(double[][] filter, Pixel[][] pixels, int i, int j, int flag) {
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

  public AbstractImage helpDither(Image image) {
    double[][] filter = new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    image = (Image) helpTransformOperate(image, filter);
    Pixel[][] pixels = image.getPixels();

    for (int i = 0; i <= pixels.length - 1; i++) {
      for (int j = 0; j <= pixels[0].length - 1; j++) {
        manipulate(pixels, i, j);
      }
    }
    image.setPixels(pixels);
    return image;
  }

  protected void manipulate(Pixel[][] pixels, int i, int j) {
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

  protected AbstractImage helpMosaic(Image image, int seeds) {
    Pixel[][] pixels = image.getPixels();
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
    image.setPixels(pixels);
    return image;
  }

  private long distance(int x1, int y1, int x2, int y2) {
    return (long) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }
}
