package model;

import org.junit.Test;

public class FileUtilsTest {

  @Test
  public void test() {
    try {
      FileUtils.output(FileUtils.getImagePixel("./src/model/timg.jpeg"), "./src/model/fxxk.png");
    } catch (Exception e) {
      e.printStackTrace();
    }

    ImageUtils.drawHorizontalRainrow(700, 700);
    ImageUtils.drawVerticalRainbow(700, 700);
    ImageUtils.drawCheckBoard(800, 800);
  }
}