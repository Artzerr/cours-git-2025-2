package org.oxyl;

public interface Figure {

  void deplacer (double distanceX, double distanceY);
  default void redimensionner(){};
  default void tourner(){};
}
