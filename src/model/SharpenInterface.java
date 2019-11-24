package model;

public interface SharpenInterface {
  /**
   * Sharpen the given image.
   *
   * @param image Given image.
   * @return Sharpend image.
   */
  AbstractImage sharpen(AbstractImage image);
}
