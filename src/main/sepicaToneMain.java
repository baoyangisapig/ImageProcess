package main;

import java.io.IOException;

import controller.BlurController;
import controller.ImageController;
import controller.SepicaToneController;
import model.FileUtils;
import model.Image;

public class sepicaToneMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    SepicaToneController controller = new SepicaToneController();
    Image sepicaTonedPanda = (Image) controller.sepicaTone(pandaImg);
    Image sepicaTonedKid = (Image) controller.sepicaTone(kidImg);
    FileUtils.save(sepicaTonedPanda, "./res/panda_sepicaTone.jpg");
    FileUtils.save(sepicaTonedKid, "./res/kid_sepicaTone.jpg");
  }
}
