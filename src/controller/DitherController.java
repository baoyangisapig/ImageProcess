package controller;

import model.AbstractImage;
import model.Image;

/**
 * The controller to dither the image.
 */
public class DitherController extends BaseController implements DitherInterface {
  @Override
  public AbstractImage dither(AbstractImage image) {
    Image img = (Image) image;
    img= (Image) helpDither(img);
    return img;
  }

}
