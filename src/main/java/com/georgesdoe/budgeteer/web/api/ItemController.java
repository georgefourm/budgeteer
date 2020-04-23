package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.Category;
import com.georgesdoe.budgeteer.domain.Item;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@RestController
public class ItemController {

    @Autowired
    ItemRepository items;

    @Autowired
    CategoryRepository categories;

    @GetMapping("/items")
    public Iterable<Item> index() {
        return items.findAll();
    }

    @PostMapping("/items")
    public Item create(@RequestParam String name,
                       @RequestParam(required = false) Long categoryId,
                       @RequestParam(required = false) BigDecimal defaultCost) {
        Item item = new Item();
        item.setName(name);
        item.setDefaultCost(defaultCost);
        if (categoryId != null) {
            Category category = categories.findById(categoryId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            item.setCategory(category);
        }
        items.save(item);
        return item;
    }

    @PutMapping("/items/{id}")
    public Item update(@PathVariable Long id,
                       @RequestParam String name,
                       @RequestParam(required = false) Long categoryId,
                       @RequestParam(required = false) BigDecimal defaultCost) {
        Item item = items.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        item.setName(name);
        item.setDefaultCost(defaultCost);
        if (categoryId != null) {
            Category category = categories.findById(categoryId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            item.setCategory(category);
        }
        items.save(item);
        return item;
    }

    @DeleteMapping("/items/{id}")
    public void delete(@PathVariable Long id) {
        Item item = items.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        items.delete(item);
    }
}
