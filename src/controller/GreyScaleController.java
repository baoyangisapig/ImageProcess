package controller;

import model.AbstractImage;
import model.Image;

/**
 * Grey scale controller.
 */
public class GreyScaleController extends BaseController implements GreyScaleInterface {

  private double[][] filter = new double[][]{
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
  };

  @Override
  public AbstractImage greyScale(AbstractImage image) {
    Image img = (Image) image;
    img = (Image) helpTransformOperate(img, filter);
    return img;
  }
}
