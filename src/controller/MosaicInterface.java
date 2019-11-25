package controller;

import model.AbstractImage;

public interface MosaicInterface {
  /**
   * mosaic the given image.
   * @param image the processed image.
   * @return the result image.
   */
  AbstractImage mosaic(AbstractImage image,int seeds);
}
