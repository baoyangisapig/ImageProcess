package main;

import java.io.IOException;

import controller.BlurController;
import controller.ImageController;
import controller.SharpenController;
import model.FileUtils;
import model.Image;

/**
 * Main method to sharpen.
 */
public class sharpenMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    SharpenController controller = new SharpenController();
    Image sharpenedPanda = (Image) controller.sharpen(pandaImg);
    Image sharpenedKid = (Image) controller.sharpen(kidImg);
    FileUtils.save(sharpenedPanda, "./res/panda_sharpen.jpg");
    FileUtils.save(sharpenedKid, "./res/kid_sharpen.jpg");
  }
}
