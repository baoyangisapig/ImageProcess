package controller;

import model.AbstractImage;

/**
 * Interface to mosaic.
 */
public interface MosaicInterface {
  /**
   * mosaic the given image.
   * @param image the processed image.
   * @return the result image.
   */
  AbstractImage mosaic(AbstractImage image,int seeds);
}
