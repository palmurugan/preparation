package com.virtualstore.product.business.mapper;

import com.virtualstore.product.data.entity.Category;
import com.virtualstore.product.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

}
