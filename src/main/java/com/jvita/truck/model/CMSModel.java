package com.jvita.truck.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "JvitaCMS")
public class CMSModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String imgFile;
 
    public CMSModel(){}

    public CMSModel(String name, String description, String imgFile){
        this.name = name;
        this.description = description;
        this.imgFile = imgFile;
    }

    public Long getid( ){return id;}
    public void setid(Long id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getImgFile(){return imgFile;}
    public void setImgFile(String imgFile){this.imgFile = imgFile;}
}