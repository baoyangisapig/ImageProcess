package controller;

import model.AbstractImage;
import model.Image;

public class SharpenController extends BaseController implements SharpenInterface {

  private double[][] filter = new double[][]{
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}
  };

  @Override
  public AbstractImage sharpen(AbstractImage image) {
    Image img = (Image) image;
    img = helpFilterOperate(img, filter);
    return img;
  }
}
