package org.owpk.repos;

import org.owpk.model.ProdRepresentation;

import java.util.List;

public interface Repository<T> {
  T getItemById(int id);
  void updateItem(T item);
  void updateItemById(int id, T item);
  void deleteItem(T item);
  void deleteItemById(int id);
  void save(T t);
  List<T> getAll();
}
