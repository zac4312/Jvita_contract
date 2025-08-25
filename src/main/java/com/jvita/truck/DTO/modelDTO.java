package com.jvita.truck.DTO;



public class modelDTO {

    private String name;
    private String description;
    private String imgFile;

    public modelDTO(String name, String description, String imgFile){
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

    public modelDTO convertToDTO(modelDTO model) {
        return new modelDTO( model.getName(),
                             model.getDescription(),
                             model.getImgFile()
                           );

    }
}