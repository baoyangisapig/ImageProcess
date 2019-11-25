package controller;

import model.AbstractImage;
/**
 * Interface to sepicaTone the image.
 */
public interface SepicaToneInterface {
  /**
   * SepicaTone image.
   *
   * @param image Given image.
   * @return Image after sepicaTone.
   */
  AbstractImage sepicaTone(AbstractImage image);
}
