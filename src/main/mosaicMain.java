package main;

import java.io.IOException;

import controller.BlurController;
import controller.ImageController;
import controller.MosaicController;
import model.FileUtils;
import model.Image;

public class mosaicMain {
  public static void main(String[] args) throws IOException {
    Image pandaImg = (Image) FileUtils.load("./res/panda.jpg");
    Image kidImg = (Image) FileUtils.load("./res/kid.jpg");
    MosaicController controller = new MosaicController();
    Image mosaicPanda = (Image) controller.mosaic(pandaImg,1000);
    Image mosaicKid = (Image) controller.mosaic(kidImg,1000);
    FileUtils.save(mosaicPanda, "./res/panda_mosaic.jpg");
    FileUtils.save(mosaicKid, "./res/kid_mosaic.jpg");
  }
}
