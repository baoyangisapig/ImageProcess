package main;

import controller.DrawController;

public class drawMain {
  public static void main(String[] args) {
    new DrawController().drawCheckBoard();
    new DrawController().drawHorizontalRainbow();
    new DrawController().drawVerticalRainbow();
  }
}
