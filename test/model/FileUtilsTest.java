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
  }
}