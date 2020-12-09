package com.matheuscordeiro.beerstockapi.mapper;

import com.matheuscordeiro.beerstockapi.dto.BeerDTO;
import com.matheuscordeiro.beerstockapi.model.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {
    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
