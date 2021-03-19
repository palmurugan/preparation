package com.com.virtualstore.inventory.business.mapper;

import com.com.virtualstore.inventory.data.entity.ItemHistory;
import com.virtualstore.inventory.dto.ItemHistoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemHistoryMapper extends EntityMapper<ItemHistoryDTO, ItemHistory> {

}
