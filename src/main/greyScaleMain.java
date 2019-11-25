package main;

import java.io.IOException;

import controller.GreyScaleController;
import model.FileUtils;
import model.Image;

/**
 * Main method to greyscale.
 */
public class greyScaleMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    GreyScaleController controller = new GreyScaleController();
    Image greyScaledPanda = (Image) controller.greyScale(pandaImg);
    Image greyScaledKid = (Image) controller.greyScale(kidImg);
    FileUtils.save(greyScaledPanda, "./res/panda_greyScale2.jpg");
    FileUtils.save(greyScaledKid, "./res/kid_greyScale2.jpg");
  }
}
