package com.matheuscordeiro.beerstockapi.service;

import com.matheuscordeiro.beerstockapi.dto.BeerDTO;
import com.matheuscordeiro.beerstockapi.exception.BeerAlreadyRegisteredException;
import com.matheuscordeiro.beerstockapi.exception.BeerNotFoundException;
import com.matheuscordeiro.beerstockapi.mapper.BeerMapper;
import com.matheuscordeiro.beerstockapi.model.Beer;
import com.matheuscordeiro.beerstockapi.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerService {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    public List<BeerDTO> getAll() {
        return beerRepository.findAll().stream().map(beerMapper::toDTO).collect(Collectors.toList());
    }

    public BeerDTO getByName(String name) throws BeerNotFoundException {
        Beer beer = beerRepository.findByName(name).orElseThrow(() -> new BeerNotFoundException(name));
        return beerMapper.toDTO(beer);
    }

    public BeerDTO save(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    private void deleteById(long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }
}
