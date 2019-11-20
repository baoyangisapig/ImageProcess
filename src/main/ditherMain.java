package main;

import java.io.IOException;

import controller.ImageController;

public class ditherMain {
  public static void main(String[] args) throws IOException {
    ImageController c1 = new ImageController();
    c1.dither("./res/panda.jpg", "./res/panda_dither.jpg");
    c1.dither("./res/kid.jpg", "./res/kid_dither.jpg");
  }
}
