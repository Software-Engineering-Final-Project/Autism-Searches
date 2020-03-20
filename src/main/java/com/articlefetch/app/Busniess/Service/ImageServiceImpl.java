package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Image;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public List<Image> getAllImages() throws IOException {
        Stream<File> fs =   getAllPNGFiles("Images");
        return convertToImages(fs);
    }

    private static List<Image> convertToImages (Stream<File> fs) throws IOException {

       return fs.map((f) -> {
                    try {
                        String relativePath = "/Images/" + f.getName();
                        return new Image("/" + f.getName(), getImageAsByteArray(relativePath));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private static Stream<File> getAllPNGFiles(String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        File[] files = new File(url.getPath()).listFiles();
        return Arrays.stream(files)
                .filter((f) -> f.getName().contains(".png"));
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
