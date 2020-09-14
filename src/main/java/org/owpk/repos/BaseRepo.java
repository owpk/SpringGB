package org.owpk.repos;

import org.owpk.model.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class BaseRepo<T extends EntityModel> implements Repository<T> {
  protected List<T> items;

  protected int search(Predicate<T> predicate) {
    for (int i = 0; i < items.size(); i++) {
      if (predicate.test(items.get(i))) {
        return i;
      }
    }
    return -1;
  }

  public BaseRepo() {
    items = new ArrayList<>();
  }

  @Override
  public T getItemById(int id) {
    int ind = search(x -> x.getId() == id);
    return ind != -1 ? items.get(ind) : null;
  }

  @Override
  public void updateItem(T item) {
    int ind  = search(Objects::isNull);
    if (ind != -1)
      items.set(ind, item);
  }

  @Override
  public void updateItemById(int id, T item) {
    int ind = search(x -> x.getId() == id);
    if (ind != -1)
      items.set(ind, item);
  }

  @Override
  public void deleteItem(T item) {
    items.remove(item);
  }

  @Override
  public void deleteItemById(int id) {
    int ind = search(x -> x.getId() == id);
    if (ind != -1)
      items.remove(ind);
  }

  @Override
  public void save(T item) {
    items.add(item);
  }

  @Override
  public List<T> getAll() {
    return items;
  }
}
