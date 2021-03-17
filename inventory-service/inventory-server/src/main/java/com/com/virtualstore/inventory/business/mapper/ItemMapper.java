package com.com.virtualstore.inventory.business.mapper;

import com.com.virtualstore.inventory.data.entity.Item;
import com.virtualstore.inventory.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {}
