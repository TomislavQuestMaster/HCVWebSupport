package hcv.spring.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Tomo.
 */
public class FileUpload{

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
