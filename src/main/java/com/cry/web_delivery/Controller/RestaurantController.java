package com.cry.web_delivery.Controller;

import com.cry.web_delivery.Service.Imp.FileStorageServiceImp;
import com.cry.web_delivery.Service.Imp.RestaurantServiceImp;
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
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    private RestaurantServiceImp restaurantServiceImp;




    @PostMapping("")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
    @RequestParam String title,@RequestParam String subTitle, @RequestParam String description
            , @RequestParam boolean is_freeship,
        @RequestParam String address, @RequestParam String open_date  ){

        ResponseData responseData = new ResponseData();
        boolean isSuccess = restaurantServiceImp.insertRestauurant( file,title,subTitle, description,
       is_freeship, address, open_date);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getHomeRestaurant( ){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getHomePageRestaurant());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurannt(@PathVariable String filename) {
        Resource resource = fileStorageServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getDetailRestaurant(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable int id){

        restaurantServiceImp.deleteRestaurant(id);


        return  new ResponseEntity<>("Delete Restaurant Success", HttpStatus.OK);
}
}
