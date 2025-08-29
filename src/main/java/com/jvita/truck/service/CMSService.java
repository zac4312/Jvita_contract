package com.jvita.truck.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jvita.truck.util.Filing; 
import com.jvita.truck.model.CMSModel;
import com.jvita.truck.repository.CMSRepository;
import com.jvita.truck.DTO.*;

import java.io.IOException;
import java.util.List;

@Service
public class CMSService {

    private final CMSRepository cmsRepository;
    private final Filing FilingTool;

    public CMSService(CMSRepository cmsRepository, Filing FilingTool){ this.cmsRepository = cmsRepository; this.FilingTool = FilingTool;}
 
    public CMSModel createModel(Input_modelDTO input_modelDTO, MultipartFile imgFile) throws IOException {

        if (!imgFile.isEmpty() || imgFile != null) {
            input_modelDTO.setImgFile(FilingTool.uploadImg(imgFile));    
        }
        
        CMSModel model = input_modelDTO.ToEntity();

        return cmsRepository.save(model);   
    }

    public List<Output_modelDTO> listModel(){
        return cmsRepository.findAll().stream()
                                      .map(Output_modelDTO::convertToDTO)
                                      .toList();
    }

    public Output_modelDTO getModel(Long id) {
        CMSModel model = cmsRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Model: " + id  + "NOT FOUND"));

            return Output_modelDTO.convertToDTO(model);
    }                   

    public void dltModel(Long id) throws IOException {
        CMSModel model = cmsRepository.findById(id).orElseThrow(() ->
                                                      new IllegalArgumentException("Model: " + id  + "NOT FOUND"));
        FilingTool.DeleteImg(model.getImgFile());
        cmsRepository.deleteById(id) ;
    }

    public void dltALL() throws IOException{
        for (CMSModel model : cmsRepository.findAll()) {        
            FilingTool.DeleteImg(model.getImgFile());
        }

        cmsRepository.deleteAll();
    }

    public CMSModel editModel(CMSModel newData, MultipartFile imgFile, Long id) throws IOException {
       CMSModel model = cmsRepository.findById(id).orElseThrow(() ->
                                                  new IllegalArgumentException("Model: " + id  + "NOT FOUND"));


    model.setName(newData.getName());
    model.setDescription(newData.getDescription());

    if (imgFile != null && !imgFile.isEmpty()) {
            FilingTool.DeleteImg(model.getImgFile());
            model.setImgFile(FilingTool.uploadImg(imgFile));
        }

    return cmsRepository.save(model);
}

}