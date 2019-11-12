package controller;

import model.ImageUtils;

/**
 * The controller for draw Method.
 *
 * @Author Yang Bao && YiMing Chu
 */
public class DrawController {
  public void drawHorizontalRainrow() {
    ImageUtils.drawHorizontalRainrow(700, 700);
  }

  public void drawVerticalRainbow() {
    ImageUtils.drawVerticalRainbow(700, 700);
  }

  public void drawCheckBoard() {
    ImageUtils.drawCheckBoard(800, 800);
  }
}
