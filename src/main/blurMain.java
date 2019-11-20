package main;

import java.io.IOException;

import controller.ImageController;

public class blurMain {
  public static void main(String[] args) throws IOException {
    ImageController c1 = new ImageController();
    double[][] filter = new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
    };
    c1.blur(filter, "./res/panda.jpg", "./res/panda_blur.jpg");
    c1.blur(filter, "./res/kid.jpg", "./res/kid_blur.jpg");
  }
}
