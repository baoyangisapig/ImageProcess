package controller;

import model.AbstractImage;
import model.Image;
import model.SepicaToneInterface;

public class SepicaToneController extends BaseController implements SepicaToneInterface {

  private double[][] filter = new double[][]{
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  @Override
  public AbstractImage sepicaTone(AbstractImage image) {
    Image img = (Image) image;
    img = helpFilterOperate(img, filter);
    return img;
  }
}
