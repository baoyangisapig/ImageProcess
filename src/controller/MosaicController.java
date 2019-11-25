package controller;

import model.AbstractImage;
import model.Image;

/**
 * The Controller to mosaic the image.
 */
public class MosaicController extends BaseController implements MosaicInterface {
  @Override
  public AbstractImage mosaic(AbstractImage image,int seeds) {
    Image img = (Image) image;
    img= (Image) helpMosaic(img,seeds);
    return img;
  }
}
