package controller;

import model.AbstractImage;

/**
 * Interface for greyScaling the image.
 */
public interface GreyScaleInterface {
  /**
   * GreyScale the given image.
   *
   * @param image Given image to be greyscaled.
   * @return Greyscaled image.
   */
  AbstractImage greyScale(AbstractImage image);
}
