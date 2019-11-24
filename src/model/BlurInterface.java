package model;

/**
 * Interface for blur.
 */
public interface BlurInterface {

  /**
   * Blur the image with default filter.
   *
   * @return Blured image.
   */
  AbstractImage blur(AbstractImage image);
}
