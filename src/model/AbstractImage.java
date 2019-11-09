package model;

public abstract class AbstractImage {
  abstract AbstractImage blur(int[][] filter);

  abstract AbstractImage sharpen(int[][] filter);

  abstract AbstractImage greyScale(int[][] filter);

  abstract AbstractImage sepicaTone(int[][] filter);
}
