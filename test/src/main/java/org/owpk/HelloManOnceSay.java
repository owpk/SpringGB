package org.owpk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("helloMan")
public class HelloManOnceSay implements HelloMan {

  @Value("Igor")
  private String name;

  public HelloManOnceSay() {
  }

  public HelloManOnceSay(String name) {
    this.name = name;
  }

  public void helloSay() {
    System.out.println("Hello, " + this.name);
  }
}
