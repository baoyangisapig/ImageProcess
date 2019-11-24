package controller;

import model.AbstractImage;
import model.BlurInterface;
import model.Image;
import model.Pixel;

/**
 * Blur Controller.
 */
public class BlurController extends BaseController implements BlurInterface {

  private double[][] filter = new double[][]{
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
  };

  @Override
  public AbstractImage blur(AbstractImage image) {
    Image img = (Image) image;
    img = helpFilterOperate(img, filter);
    return img;
  }
}
