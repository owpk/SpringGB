package org.owpk;

import org.springframework.stereotype.Component;

@Component("blackAndWhiteCameraRoll")
public class BlackWhiteCameraRoll implements CameraRoll {

  public String printPhoto() {
    return "Black & white shot";
  }
}
