package com.jvita.truck.DTO;

import com.jvita.truck.model.CMSModel;

public class Output_modelDTO {

    private Long id;
    private String name;
    private String description;
    private String imgFile;

    public Output_modelDTO(Long id ,String name, String description, String imgFile){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgFile = imgFile;
    }

    public Long getid( ){return id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getImgFile(){return imgFile;}
    public void setImgFile(String imgFile){this.imgFile = imgFile;}

    public static Output_modelDTO convertToDTO(CMSModel model) {
        return new Output_modelDTO( model.getid(),
                                    model.getName(),
                                    model.getDescription(),
                                    model.getImgFile()
                                  );
    }

}

