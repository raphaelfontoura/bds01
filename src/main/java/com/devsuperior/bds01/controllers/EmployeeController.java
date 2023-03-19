package com.devsuperior.bds01.controllers;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.repositories.EmployeeRepository;
import com.devsuperior.bds01.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployeePaged(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaged(pageable));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO dto) {
        EmployeeDTO employee = service.createNewEmployee(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();
        return ResponseEntity.created(uri).body(employee);
    }

}
