package com.virtualstore.product.business.mapper;

import com.virtualstore.product.data.entity.Product;
import com.virtualstore.product.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

}
