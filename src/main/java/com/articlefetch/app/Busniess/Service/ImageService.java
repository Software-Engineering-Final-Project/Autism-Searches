package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Image;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    public List<Image> getAllImages() throws IOException;
}
