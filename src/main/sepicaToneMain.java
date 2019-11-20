package main;

import java.io.IOException;

import controller.ImageController;

public class sepicaToneMain {
  public static void main(String[] args) throws IOException {
    ImageController c1 = new ImageController();
    double[][] filter3 = new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    c1.sepicaTone(filter3, "./res/panda.jpg", "./res/panda_sepicaTone.jpg");
    c1.sepicaTone(filter3, "./res/kid.jpg", "./res/kid_sepicaTone.jpg");
  }
}
