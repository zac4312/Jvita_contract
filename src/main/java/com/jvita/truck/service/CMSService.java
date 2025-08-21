package com.jvita.truck.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jvita.truck.util.Util; 
import com.jvita.truck.model.CMSModel;
import com.jvita.truck.repository.CMSRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CMSService {

    private final CMSRepository cmsRepository;
    private final Util util;

    public CMSService(CMSRepository cmsRepository, Util util){ this.cmsRepository = cmsRepository; this.util = util;}


    public CMSModel createModel(CMSModel model, MultipartFile imgFile) throws IOException {

        if (imgFile != null && !imgFile.isEmpty()) {
            model.setImgFile(util.uploadImg(imgFile));
        }

        System.out.println(" ");
        System.out.println("SERVICE");
        System.out.println("MODEL: " + model);
        System.out.println("IMAGE: " + model.getImgFile());
        return cmsRepository.save(model);   
    }

    public List<CMSModel> listModel(){
        return cmsRepository.findAll();
    }

    public Optional<CMSModel> IdModel(Long id) {
        return cmsRepository.findById(id);
    }

    public void dltModel(Long id){
        cmsRepository.deleteById(id) ;
    }

    public void dltALL(){
        cmsRepository.deleteAll(); ;
    }

    public CMSModel editModel(CMSModel newData, MultipartFile imgFile, Long id) throws IOException {
    CMSModel model = cmsRepository.findById(id).orElseThrow(() -> 
        new IllegalArgumentException("Item does not exist")
    );

    model.setName(newData.getName());
    model.setDescription(newData.getDescription());

    if (imgFile != null && !imgFile.isEmpty()) {
            model.setImgFile(util.uploadImg(imgFile));
        }

    return cmsRepository.save(model);
}

}