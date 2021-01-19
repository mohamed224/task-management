package com.owntech.taskmanagement.dao;

import com.owntech.taskmanagement.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends BaseDao<Category, Long> {
}
