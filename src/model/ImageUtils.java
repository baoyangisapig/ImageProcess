package model;

import java.awt.*;
import java.io.IOException;

/**
 * Util class for drawing pictures.
 */
public class ImageUtils {

  static Color red = new Color(255, 0, 0);
  static Color orange = new Color(255, 165, 0);
  static Color yellow = new Color(255, 255, 0);
  static Color green = new Color(0, 255, 0);
  static Color cyan = new Color(0, 127, 255);
  static Color blue = new Color(0, 0, 255);
  static Color purple = new Color(139, 0, 255);
  static Color[] rainbows = new Color[]{red, orange, yellow, green, cyan, blue, purple};

  /**
   * Draw horizontal rainbow.
   *
   * @param width  Width of the picture.
   * @param height Height of the picture.
   */
  public static void drawHorizontalRainrow(int width, int height) {
    // Generate pixels of vertical rainbow.
    Pixel[][] pixels = new Pixel[width][height];
    int stripeHeight = height / 7;
    for (int i = 0; i < 7; i++) {
      for (int x = i * stripeHeight; x < (i + 1) * stripeHeight; x++) {
        for (int y = 0; y < width; y++) {
          Color cur = rainbows[i];
          pixels[x][y] = new Pixel(cur);
        }
      }
    }
    // Draw horizontal rainbow.
    try {
      FileUtils.output(pixels, "src/model/horizontal_rainbow.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Draw vertical rainbow.
   *
   * @param width  Width of the picture.
   * @param height Height of the picture.
   */
  public static void drawVerticalRainbow(int width, int height) {
    // Generate pixels of the vertical rainbow.
    Pixel[][] pixels = new Pixel[width][height];
    int stripeWidth = width / 7;
    for (int i = 0; i < 7; i++) {
      for (int x = 0; x < height; x++) {
        for (int y = i * stripeWidth; y < (i + 1) * stripeWidth; y++) {
          Color cur = rainbows[i];
          pixels[x][y] = new Pixel(cur);
        }
      }
    }
    // Draw rainbow.
    try {
      FileUtils.output(pixels, "src/model/vertical_rainbow.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Draw check board.
   *
   * @param width  Width of the check board.
   * @param height Height of the check board.
   */
  public static void drawCheckBoard(int width, int height) {
    // Generate pixels of check board.
    Pixel[][] pixels = new Pixel[width][height];
    int w = width / 8;
    int h = height / 8;
    for (int i = 0; i < 8; i++) {
      int top = i * h;
      int bottom = (i + 1) * h;
      for (int j = 0; j < 8; j++) {
        Color color;
        if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
          color = Color.white;
        } else {
          color = Color.black;
        }
        int left = j * w;
        int right = (j + 1) * w;
        for (int m = top; m < bottom; m++) {
          for (int n = left; n < right; n++) {
            pixels[m][n] = new Pixel(color);
          }
        }
      }
    }
    // Draw check board.
    try {
      FileUtils.output(pixels, "src/model/check_board.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
