package com.cry.web_delivery.Service;

import com.cry.web_delivery.Service.Imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService implements FileStorageServiceImp {

    @Value("${path.upload.file}")
    private String folderRoot;

    private Path root ;


    private void init() {
        try {
            root = Paths.get(folderRoot);
            if (Files.exists(root)) {
                Files.createDirectories(root);

            }
        }catch(Exception e){
            System.out.println("Erorr create folder root:" + e.getLocalizedMessage());
        }
    }
    @Override
    public boolean saveFile(MultipartFile file) {

        try{
            init();
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return  true;
        }catch (Exception e){
            System.out.println("Errorr save  file: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try{
            init();
            Path file = root.resolve(fileName);
            Resource resource  = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
        }catch (Exception e) {
            System.out.println("Error load file" + e.getLocalizedMessage());
        }
        return  null;
    }

}
