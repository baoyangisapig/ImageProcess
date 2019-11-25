package controller;

import model.AbstractImage;

public interface DitherInterface {
  /**
   * dither the given image.
   * @param abstractImage processed image.
   * @return  the result image.
   */
  AbstractImage dither(AbstractImage abstractImage);
}
