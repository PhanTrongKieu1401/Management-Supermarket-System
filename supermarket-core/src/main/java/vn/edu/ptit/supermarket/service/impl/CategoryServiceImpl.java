package vn.edu.ptit.supermarket.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.ptit.supermarket.entity.Category;
import vn.edu.ptit.supermarket.repository.CategoryRepository;
import vn.edu.ptit.supermarket.service.CategoryService;

@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public List<Category> findAll() {
    log.info("findAll");
    return categoryRepository.findAll();
  }
}
