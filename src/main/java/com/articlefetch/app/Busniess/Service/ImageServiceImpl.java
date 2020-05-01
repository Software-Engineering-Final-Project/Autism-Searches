package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Image;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements ImageService {
    final String PARENT_FOLDER = "Images";
    Set<String> imageSet = new HashSet<>(Arrays.asList("c++.png", "c.png", "clojure.png", "csharp.png", "default_user.png",
            "elixir.png", "erlang.png", "github.png", "go.png", "haskell.png", "html.png", "java_logo.png", "java_mascot.png," ,
                    "javascript_logo.png", "kotlin.png", "php.png", "python.png", "racket.png", "react_logo.png", "ruby.png",
            "rust.png", "scala.png", "swift.png"));


    @Override
    public List<Image> getAllImages() throws IOException {
        return convertToImages();
    }

    private List<Image> convertToImages () throws IOException {

        Iterator<String> it = this.imageSet.iterator();
        ArrayList<Image> img_list = new ArrayList<>();
        for(String path: imageSet) {
            String relativePath = "/"+ PARENT_FOLDER + "/" + path;
            img_list.add(new Image("/" + path,  getImageAsByteArray(relativePath)));
        }

        return img_list;
    }


    private static byte[] getImageAsByteArray(String path) throws IOException {
        try {
            InputStream inputStream = Mapper.class
                    .getResourceAsStream(path);
            return inputStream.readAllBytes();
        } catch (Exception e) {
            // If there is not a valid path we will return null
            // TODO: Better Error Handling!
            return null;
        }
    }
}
