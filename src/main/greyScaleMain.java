package main;

import java.io.IOException;

import controller.ImageController;

public class greyScaleMain {
  public static void main(String[] args) throws IOException {
    ImageController c1 = new ImageController();
    double[][] filter2 = new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    c1.greyScale(filter2, "./res/panda.jpg", "./res/panda_greyScale.jpg");
    c1.greyScale(filter2, "./res/kid.jpg", "./res/kid_greyScale.jpg");
  }
}
