package model;

public abstract class AbstractImage {
  abstract AbstractImage blur(double[][] filter);

  abstract AbstractImage sharpen(double[][] filter);

  abstract AbstractImage greyScale(double[][] filter);

  abstract AbstractImage sepicaTone(double[][] filter);
}
