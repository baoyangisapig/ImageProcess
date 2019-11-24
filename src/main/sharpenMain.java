package main;

import java.io.IOException;

import controller.BlurController;
import controller.ImageController;
import controller.SharpenController;
import model.FileUtils;
import model.Image;

public class sharpenMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    SharpenController controller = new SharpenController();
    Image bluredPanda = (Image) controller.sharpen(pandaImg);
    Image bluredKid = (Image) controller.sharpen(kidImg);
    FileUtils.save(bluredPanda, "./res/panda_sharpen2.jpg");
    FileUtils.save(bluredKid, "./res/kid_sharpen2.jpg");
  }
}
