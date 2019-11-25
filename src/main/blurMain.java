package main;

import java.io.IOException;

import controller.BlurController;
import model.FileUtils;
import model.Image;

/**
 * Main method to blur.
 */
public class blurMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    BlurController blurController = new BlurController();
    Image bluredPanda = (Image) blurController.blur(pandaImg);
    Image bluredKid = (Image) blurController.blur(kidImg);
    FileUtils.save(bluredPanda, "./res/panda_blur.jpg");
    FileUtils.save(bluredKid, "./res/kid_blur.jpg");
  }
}
