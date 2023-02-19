package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.common.Category;
import com.georgesdoe.budgeteer.domain.expense.Item;
import com.georgesdoe.budgeteer.repository.CategoryRepository;
import com.georgesdoe.budgeteer.repository.ItemRepository;
import com.georgesdoe.budgeteer.web.request.ItemRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public Item create(@RequestBody ItemRequest request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setDefaultCost(request.getDefaultCost());
        Long categoryId = request.getCategoryId();
        if (categoryId != null) {
            Category category = categories.findById(categoryId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            item.setCategory(category);
        }
        items.save(item);
        return item;
    }

    @PutMapping("/items/{id}")
    public Item update(@PathVariable Long id, @RequestBody ItemRequest request) {
        Item item = items.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        item.setName(request.getName());
        item.setDefaultCost(request.getDefaultCost());
        Long categoryId = request.getCategoryId();
        if (categoryId != null) {
            Category category = categories.findById(categoryId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            item.setCategory(category);
        }
        items.save(item);
        return item;
    }

    @DeleteMapping("/items/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        Item item = items.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        items.delete(item);
        return new SimpleMessageResponse("Item deleted");
    }
}
