package model;

import model.AbstractImage;

public class Image extends AbstractImage {
  double width;
  double length;
  Pixel[][] pixel;

  public Image(double width, double length, Pixel[][] pixel) {
    this.width = width;
    this.length = length;
    this.pixel = pixel;
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

  public Pixel[][] getPixel() {
    return pixel;
  }

  public void setPixel(Pixel[][] pixel) {
    this.pixel = pixel;
  }

  @Override
  public AbstractImage blur() {
    return null;
  }

  @Override
  public AbstractImage sharpen() {
    return null;
  }
}
