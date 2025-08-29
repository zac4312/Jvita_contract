package com.jvita.truck.DTO;

import com.jvita.truck.model.CMSModel;

public class Input_modelDTO {

    private String name;
    private String description;
    private String imgFile;

    public Input_modelDTO(String name, String description, String imgFile){
        this.name = name;
        this.description = description;
        this.imgFile = imgFile;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getImgFile(){return imgFile;}
    public void setImgFile(String imgFile){this.imgFile = imgFile;}

    public CMSModel ToEntity() {
        return new CMSModel( this.getName(),
                             this.getDescription(),
                             this.getImgFile() 
        );
    }

}
