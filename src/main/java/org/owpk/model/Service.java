package org.owpk.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class Service {
  private static final SessionFactory factory;
  static {
    factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();
  }
  private Session session;

  public List<ProductEntity> getProductListForCustomer(String name) {
    session = factory.openSession();
    session.beginTransaction();
    Query query = session.createQuery(
        "SELECT p FROM ProductEntity p LEFT OUTER JOIN p.customers c WHERE c.name = :name", ProductEntity.class);
    query.setParameter("name", name);
    List<ProductEntity> result = query.getResultList();
    session.getTransaction().commit();
    return result;
  }

  public List<CustomerEntity> getCustomerListForProduct(String prod) {
    session = factory.openSession();
    session.beginTransaction();
    Query query = session.createQuery(
        "SELECT c FROM CustomerEntity c LEFT OUTER JOIN c.products p WHERE p.name = :name", CustomerEntity.class);
    query.setParameter("name", prod);
    List<CustomerEntity> result = query.getResultList();
    session.getTransaction().commit();
    return result;
  }

  public void delete(String name, Class<?> c) {
    session = factory.openSession();
    session.beginTransaction();
    Query query = session.createQuery("SELECT o FROM " + c.getSimpleName() + " o WHERE o.name = :name");
    query.setParameter("name", name);
    Object o = query.getSingleResult();
    System.out.println(o);
    System.out.println(o.getClass().getSimpleName());
    session.delete(o);
    session.getTransaction().commit();
  }
}
