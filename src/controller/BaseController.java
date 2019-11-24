package controller;

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
}
