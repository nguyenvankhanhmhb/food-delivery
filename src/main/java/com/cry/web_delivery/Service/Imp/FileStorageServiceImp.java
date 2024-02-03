package com.cry.web_delivery.Service.Imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceImp {
    boolean saveFile(MultipartFile file);
    Resource loadFile(String fileName);
}
