package com.penguins.project.model.Picture;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;


    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public void uploadImage(String filePath){

        Picture picture = Picture.builder()
                .filePath(filePath)
                .build();
        pictureRepository.save(picture);
    }
    /*
    public byte[] getImage(Long id){
        return pictureRepository.findById(id).get().getPicture();
    }

     */
}
