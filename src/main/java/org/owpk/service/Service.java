package org.owpk.service;

import org.owpk.model.ProdRepresentation;

import java.util.List;

public interface Service {
  void update(ProdRepresentation prod);
  void insert(ProdRepresentation prod);
  void deleteById(int id);
  List<ProdRepresentation> getAllRepresentation();
}
