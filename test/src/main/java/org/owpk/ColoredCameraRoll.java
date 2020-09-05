package org.owpk;

import org.springframework.stereotype.Component;

@Component("coloredCameraRoll")
public class ColoredCameraRoll implements CameraRoll {
  public String printPhoto() {
    return "Colored photo shot";
  }
}
