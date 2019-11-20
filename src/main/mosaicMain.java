package main;

import java.io.IOException;

import controller.ImageController;

public class mosaicMain {
  public static void main(String[] args) throws IOException {
    ImageController c1 = new ImageController();
    c1.mosaic("./res/panda.jpg", "./res/panda_mosaic.jpg", 4000);
    c1.mosaic("./res/kid.jpg", "./res/kid_mosaic.jpg", 4000);
  }
}
