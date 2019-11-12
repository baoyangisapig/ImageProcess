package model;

import java.awt.*;

public class Pixel {
  private int r;
  private int g;
  private int b;

  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

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
