package com.example.prospecta.controller;


import com.example.prospecta.model.Data;
import com.example.prospecta.model.Entry;
import com.example.prospecta.model.DTOres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/entries/{category}")
    public ResponseEntity<Object> getEntriesByCategoriesHandler(@PathVariable("category") String category) {
        String url = "https://api.publicapis.org/entries";
        Data data = restTemplate.getForObject(url, Data.class);
        List<Entry> list = data.getEntries();
        List<DTOres> result = new ArrayList<>();
        for (Entry e : list) {
            if (e.getCategory()
                    .equals(category)) {
                DTOres dtOres = new DTOres(e.getApi(), e.getDescription());
                result.add(dtOres);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/entries")
    public ResponseEntity<Object> getEntriesByCategoriesHandler(@RequestBody Entry entry) {

        String url = "https://api.publicapis.org/entries";

        Data data = restTemplate.getForObject(url, Data.class);

        List<Entry> list = data.getEntries();

        list.add(entry);
        return new ResponseEntity<Object>(list, HttpStatus.OK);
    }
}
