package jgaudio.katas.checkout;

public enum Item {
  BANANA(1d), WATERMELON(2d), COFFEE(3d), MILK(4d);

  Item(double unitaryPrice) {
    this.unitaryPrice = unitaryPrice;
  }

  double unitaryPrice;

  public double getUnitaryPrice() {
    return unitaryPrice;
  }
}
