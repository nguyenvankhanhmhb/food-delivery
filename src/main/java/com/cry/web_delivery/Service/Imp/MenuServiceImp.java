package com.cry.web_delivery.Service.Imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
   boolean createMenu(MultipartFile file,  String title,  String is_freeship, String timme_ship,
                            double price, int cate_id );
}
