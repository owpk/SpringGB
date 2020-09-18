package org.owpk.repos;

import org.owpk.model.Category;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("categoryRepo")
public class CategoryRepo extends BaseRepo<Category> {

  @PostConstruct
  void fill() {
    items.add(new Category(1, "Cat1"));
    items.add(new Category(2, "Cat2"));
  }

  public Category getCategoryByName(String name) {
    int ind = search(x -> x.getName().equals(name));
    return items.get(ind);
  }
}
