package org.owpk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Camera {

  private final CameraRoll cameraRoll;

  @Autowired
  public Camera(@Qualifier(value = "coloredCameraRoll") CameraRoll cameraRoll) {
    this.cameraRoll = cameraRoll;
  }

  public String doPhoto() {
    return "The camera is taking photo: " + cameraRoll.printPhoto();
  }
}
