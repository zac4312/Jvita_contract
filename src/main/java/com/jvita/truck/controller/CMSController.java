package com.jvita.truck.controller;
    
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jvita.truck.model.CMSModel;
import com.jvita.truck.service.CMSService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CMSController {

    private final CMSService service;

    public CMSController(CMSService service){ this.service = service; }
         
   @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createModels(@RequestPart("model") CMSModel model, 
                                            @RequestPart("imgFile")MultipartFile imgFile) {

    System.out.println(" ");
    System.out.println("CONTROLLER");
    System.out.println("MODEL: " + model);
    System.out.println("NAME: " + model.getName());
    System.out.println("DESCRIPTION: " + model.getDescription());
       try{
        CMSModel saved = service.createModel(model, imgFile);
        return ResponseEntity.ok(saved);
       }catch(IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload content: " + e.getMessage());
       }
   }

   @GetMapping("/products")
   public List<CMSModel> listModels() {
       return service.listModel();
   }
   
   @GetMapping("/products/{id}")
   public Optional<CMSModel> IdModels(@PathVariable Long id) {
    return service.IdModel(id);
   }

   @DeleteMapping("/dlt/{id}")
   public ResponseEntity<Void> dltModel(@PathVariable Long id){
     service.dltModel(id);
     return ResponseEntity.noContent().build();
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

    @DeleteMapping("dlt-all")
    public ResponseEntity<Void> deleteAll() {
        service.dltALL();
        return ResponseEntity.noContent().build();
    }
}
