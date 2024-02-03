package com.cry.web_delivery.Controller;

import com.cry.web_delivery.Service.Imp.FileStorageServiceImp;
import com.cry.web_delivery.Service.Imp.MenuServiceImp;
import com.cry.web_delivery.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuServiceImp menuServiceImp;
    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;


    @PostMapping("")
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
             @RequestParam String title, @RequestParam String is_freeship, @RequestParam String timme_ship,
             @RequestParam double price, @RequestParam int cate_id  ){

        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuServiceImp.createMenu(file,title,is_freeship,timme_ship,price, cate_id );
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurannt(@PathVariable String filename) {
        Resource resource = fileStorageServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


}
