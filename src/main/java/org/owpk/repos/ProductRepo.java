package org.owpk.repos;

import org.owpk.model.ProdRepresentation;
import org.owpk.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component("prodRepo")
public class ProductRepo extends BaseRepo<Product> {
  private static AtomicInteger counter;
  private CategoryRepo category;

  @Autowired
  public void setCategory(CategoryRepo category) {
    this.category = category;
  }

  @PostConstruct
  void fill() {
    counter = new AtomicInteger();
    items.add(new Product(incrementId(), "P1", 200, category.getItemById(1)));
    items.add(new Product(incrementId(), "P2", 300, category.getItemById(2)));
    items.add(new Product(incrementId(), "P3", 400, category.getItemById(2)));
  }

  public static int incrementId() {
    return counter.incrementAndGet();
  }
  public static int get() { return counter.get(); }

  public List<ProdRepresentation> getAllRepresentation() {
    return items.stream()
        .map(x -> new ProdRepresentation(x.getId(), x.getTitle(), x.getCost(), x.getCategory().getName()))
        .collect(Collectors.toList());
  }

}
