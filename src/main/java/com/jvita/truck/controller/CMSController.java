package com.jvita.truck.controller;
    
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jvita.truck.DTO.*;
import com.jvita.truck.model.CMSModel;
import com.jvita.truck.service.CMSService;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "*")

public class CMSController {

    private final CMSService service;
    private final Logger log = LoggerFactory.getLogger(CMSController.class);

    public CMSController(CMSService service){ this.service = service;}
         
   @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createModels(@RequestPart("model") Input_modelDTO inputDTO, 
                                          @RequestPart("imgFile")MultipartFile imgFile) {
                        
    log.info("Model: " + inputDTO);
    log.info("Name: " + inputDTO.getName());
    log.info("Description: " + inputDTO.getDescription());
    log.info("Image: " + inputDTO.getImgFile());
    
       try{
        CMSModel saved = service.createModel(inputDTO, imgFile);
        return ResponseEntity.ok(saved);
       }catch(IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload content: " + e.getMessage());
       }
   }

   @GetMapping("/products")
   public String listModels(Model model){ 
   List<Output_modelDTO> products = service.listModel();
   model.addAttribute("products", products);
   return "products";
  }
   
   @GetMapping("/products/{id}")
   public Output_modelDTO IdModels(@PathVariable Long id) {
    return service.getModel(id);
   }

   @DeleteMapping("/dlt/{id}")
   public ResponseEntity<Void> dltModels(@PathVariable Long id) { 
    try
    { service.dltModel(id);
      return ResponseEntity.noContent().build();
    }
      catch (IOException e) 
        { return ResponseEntity.noContent().build(); }
    
   }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> editModels(@PathVariable Long id,
                                            @RequestPart("model") CMSModel model,
                                            @RequestPart("imgFile") MultipartFile imgFile) {

        try {
            CMSModel updated = service.editModel(model, imgFile, id);
            return ResponseEntity.ok(updated);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update content: " + e.getMessage());
              }
    }

    @DeleteMapping("/dlt-all")
    public ResponseEntity<Void> deleteAll() {
        try
        { service.dltALL();
          return ResponseEntity.noContent().build();
        }
         catch (IOException e)
         { return ResponseEntity.noContent().build();}
    }
}
