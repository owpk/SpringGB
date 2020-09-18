package org.owpk.service;

import org.owpk.model.Category;
import org.owpk.model.ProdRepresentation;
import org.owpk.model.Product;
import org.owpk.repos.CategoryRepo;
import org.owpk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService implements Service {

  private ProductRepo productRepository;
  private CategoryRepo categoryRepository;

  @Autowired
  public void setProductRepository(ProductRepo repository) {
    this.productRepository = repository;
  }

  @Autowired
  public void setCategoryRepository(CategoryRepo repository) {
    this.categoryRepository = repository;
  }

  public void update(ProdRepresentation prod) {
    Category category = categoryRepository.getCategoryByName(prod.getCategory());
    productRepository.updateItem(
        new Product(ProductRepo.incrementId(), prod.getName(), prod.getCost(), category));
  }

  public void insert(ProdRepresentation prod) {
    Category category = categoryRepository.getCategoryByName(prod.getCategory());
    productRepository.save(
        new Product(ProductRepo.incrementId(),prod.getName(), prod.getCost(), category));
  }

  public ProdRepresentation findById(int id) {
    Product prod = productRepository.getItemById(id);
    return new ProdRepresentation(
        prod.getId(), prod.getTitle(), prod.getCost(), prod.getCategory().getName());
  }

  public void deleteById(int id) {
    productRepository.deleteItemById(id);
  }

  public List<ProdRepresentation> getAllRepresentation() {
    return productRepository.getAllRepresentation();
  }

}
