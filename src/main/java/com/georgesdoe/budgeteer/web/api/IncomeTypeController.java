package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.IncomeType;
import com.georgesdoe.budgeteer.repository.IncomeTypeRepository;
import com.georgesdoe.budgeteer.web.request.IncomeTypeRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class IncomeTypeController {

    @Autowired
    IncomeTypeRepository incomeTypes;

    @GetMapping("/income-types")
    public Iterable<IncomeType> index() {
        return incomeTypes.findAll();
    }

    @PostMapping("/income-types")
    public IncomeType create(@RequestBody IncomeTypeRequest request) {
        IncomeType type = new IncomeType();
        type.setName(request.getName());
        Long parentId = request.getParentId();
        if (parentId != null) {
            IncomeType parent = incomeTypes.findById(parentId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            type.setParent(parent);
        }
        incomeTypes.save(type);
        return type;
    }

    @PutMapping("/income-types/{id}")
    public IncomeType update(@PathVariable Long id,@RequestBody IncomeTypeRequest request) {
        IncomeType type = incomeTypes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        type.setName(request.getName());
        Long parentId = request.getParentId();
        if (parentId != null) {
            IncomeType parent = incomeTypes.findById(parentId)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
            type.setParent(parent);
        }
        incomeTypes.save(type);
        return type;
    }

    @DeleteMapping("/income-types/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        IncomeType type = incomeTypes.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        incomeTypes.delete(type);
        return new SimpleMessageResponse("Income type deleted");
    }
}
