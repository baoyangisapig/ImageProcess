package main;

import java.io.IOException;

import controller.DitherController;
import model.FileUtils;
import model.Image;

public class ditherMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    DitherController ditherController = new DitherController();
    Image ditherPanda = (Image) ditherController.dither(pandaImg);
    Image bluredKid = (Image) ditherController.dither(kidImg);
    FileUtils.save(ditherPanda, "./res/panda_dither.jpg");
    FileUtils.save(bluredKid, "./res/kid_dither.jpg");
  }
}
