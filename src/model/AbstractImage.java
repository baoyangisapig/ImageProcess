package model;

/**
 * The class represent the general items of image.
 * @Author Yang Bao&&YiMing Chu
 */
public abstract class AbstractImage {
  /**
   * blur the image with a filter.
   * @param filter filter array.
   * @return the current image.
   */
  abstract AbstractImage blur(double[][] filter);

  /**
   * sharpen the image with a filter
   * @param filter filter array.
   * @return the current image.
   */
  abstract AbstractImage sharpen(double[][] filter);

  /**
   * greyScale the image with a filter.
   * @param filter filter array.
   * @return the current image.
   */
  abstract AbstractImage greyScale(double[][] filter);

  /**
   * sepicaTone the image with a filter.
   * @param filter filter array.
   * @return the current image.
   */
  abstract AbstractImage sepicaTone(double[][] filter);
}
