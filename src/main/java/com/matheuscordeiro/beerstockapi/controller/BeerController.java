package com.matheuscordeiro.beerstockapi.controller;

import com.matheuscordeiro.beerstockapi.dto.BeerDTO;
import com.matheuscordeiro.beerstockapi.exception.BeerAlreadyRegisteredException;
import com.matheuscordeiro.beerstockapi.exception.BeerNotFoundException;
import com.matheuscordeiro.beerstockapi.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/beers")
public class BeerController {
    @Autowired
    BeerService beerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDTO> getAll() {
        return beerService.getAll();
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDTO getByName(@PathVariable String name) throws BeerNotFoundException {
        return  beerService.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO save(@RequestBody @Valid  BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        return beerService.save(beerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }
}
