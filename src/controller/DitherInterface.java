package controller;

import model.AbstractImage;

/**
 * Interface for dithering.
 */
public interface DitherInterface {
  /**
   * dither the given image.
   * @param abstractImage processed image.
   * @return  the result image.
   */
  AbstractImage dither(AbstractImage abstractImage);
}
