package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("country")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping(value = "", consumes =  "application/json", produces = "application/json")
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Country> getAll(@RequestParam String capitalName, @RequestParam(required = false) Boolean useSql) {
        if(useSql != null && useSql) {
            return countryService.getCountriesByCapitalNameSql(capitalName);
        } else {
            return countryService.getCountriesByCapitalNameJpa(capitalName);
        }
    }

}
