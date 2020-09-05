package org.owpk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
    Camera camera = ctx.getBean("camera", Camera.class);
    System.out.println(camera.doPhoto());

    HelloMan helloMan = ctx.getBean("helloMan", HelloManOnceSay.class);
    helloMan.helloSay();
  }
}
