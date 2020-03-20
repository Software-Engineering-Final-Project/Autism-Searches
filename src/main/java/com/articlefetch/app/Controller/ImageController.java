package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.ImageService;
import com.articlefetch.app.Controller.JacksonModels.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService service;

    @GetMapping("/all")
    public ResponseEntity<List<Image>> getAllImages() throws IOException {
        List<Image> imageList = service.getAllImages();

        return new ResponseEntity<>(imageList, HttpStatus.OK);
    }
}
