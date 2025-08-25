package com.jvita.truck.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jvita.truck.util.Filing; 
import com.jvita.truck.model.CMSModel;
import com.jvita.truck.repository.CMSRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CMSService {

    private final CMSRepository cmsRepository;
    private final Filing FilingTool;

    public CMSService(CMSRepository cmsRepository, Filing FilingTool){ this.cmsRepository = cmsRepository; this.FilingTool = FilingTool;}


    public CMSModel createModel(CMSModel model, MultipartFile imgFile) throws IOException {

        if (!imgFile.isEmpty() || imgFile != null) {
            model.setImgFile(FilingTool.uploadImg(imgFile));    
        }
        

        return cmsRepository.save(model);   
    }

    public List<CMSModel> listModel(){
        return cmsRepository.findAll();
    }

    public CMSModel getModel(Long id) {
        return cmsRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Model: " + id  + "NOT FOUND"));
    }                   

    public void dltModel(Long id) throws IOException {
        CMSModel model = getModel(id);
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
    CMSModel model = getModel(id);

    model.setName(newData.getName());
    model.setDescription(newData.getDescription());

    if (imgFile != null && !imgFile.isEmpty()) {
            FilingTool.DeleteImg(model.getImgFile());
            model.setImgFile(FilingTool.uploadImg(imgFile));
        }

    return cmsRepository.save(model);
}

}