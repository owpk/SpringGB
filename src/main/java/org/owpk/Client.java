package org.owpk;

import org.hibernate.HibernateException;
import org.owpk.model.CustomerEntity;
import org.owpk.model.ProductEntity;
import org.owpk.model.Service;

import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    //commands listener:
    Scanner sc = new Scanner(System.in);
    String cmd;
    Service service = new Service();
    while (!((cmd = sc.nextLine()).equals("q"))) {
      try {
        executeCmd(cmd, service, sc);
      } catch (HibernateException e) {
        e.printStackTrace();
      }
    }
  }

  //Подскажите, если я хочу найти покупателя по id, мне нужно сделать что то на подобе:
  // serv.getProdList(...).forEach( ... -> ... sout(searchCustomerById(x.getCustomersList(), id) + " : " + product); ?
  // то есть в сущности Product я возьму лист покупаетелй и буду искать в нем id по имени ?
  // Как вариант можно создать view в бд и соответсвенно создать в приложении для него сущность,
  // но тогда не понятно как для этой сущности сделать POJO класс,
  // или можно создать еще один дпополнительный запрос, найти покупателя по id, взять его имя и
  // печатать вместе с найденными продуктами,
  // или может можно как то из текущей конфигурации классов/сущностей так сделать
  // (какой то особый jpql запрос или еще что то) ?
  private static void executeCmd(String cmd, Service service, Scanner sc) {
    if(cmd.startsWith("prodListForCustomer")) {
      String name = getName(sc);
      service.getProductListForCustomer(name)
          .forEach(x -> System.out.println("Customer : " + name + ", Product : " + x));
    } else if(cmd.startsWith("customerListForProduct")) {
      String prod = getName(sc);
      service.getCustomerListForProduct(prod)
          .forEach(x -> System.out.println("Product: " + prod + ", Customer : " + x));
    } else if(cmd.startsWith("deleteProduct")) {
      service.delete(getName(sc), ProductEntity.class);
    } else if(cmd.startsWith("deleteCustomer")) {
      service.delete(getName(sc), CustomerEntity.class);
    }
  }

  private static String getName(Scanner sc) {
    System.out.println("Enter name: ");
    return sc.nextLine();
  }

}
