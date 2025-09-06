package com.jvita.truck.controller;

import org.springframework.web.bind.annotation.*;

import com.jvita.truck.DTO.Output_modelDTO;
import com.jvita.truck.service.CMSService;

import java.util.List;

@RestController
@RequestMapping("/page")
public class publicController {
    
    private final CMSService service;

    public publicController(CMSService service) { this.service = service; }

    @GetMapping("/products")
   public List<Output_modelDTO> listModels() {
       return service.listModel();
   }
   
   @GetMapping("/products/{id}")
   public Output_modelDTO IdModels(@PathVariable Long id) {
    return service.getModel(id);
   }

}
