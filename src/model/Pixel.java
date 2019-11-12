package model;

import java.awt.*;

/**
 * The class represents a special color with r g b.
 */
public class Pixel {
  private int r;
  private int g;
  private int b;

  /**
   * The constructor for the class.
   * @param r dimension r.
   * @param g dimension g.
   * @param b dimension b.
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * use color to constructor the class.
   * @param color a particular color.
   */
  public Pixel(Color color) {
    this.r = color.getRed();
    this.g = color.getGreen();
    this.b = color.getBlue();
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }
}
