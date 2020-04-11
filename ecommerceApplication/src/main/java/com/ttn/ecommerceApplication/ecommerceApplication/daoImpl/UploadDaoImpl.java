package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.UploadDao;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.image.ImageWatched;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadDaoImpl implements UploadDao
{

    @Override
    public ResponseEntity<Object> uploadSingleImage(MultipartFile file) throws IOException {
        File convertfile = new File("/home/himanshu/Downloads/ecommerceApplication/src/main/resources/upload/images"+file.getOriginalFilename());
        convertfile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertfile);
        System.out.println(convertfile.getAbsolutePath());
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("file added", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> uploadMultipleFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile multipartFile : files)
        {
            File convertfile = new File("/home/himanshu/Downloads/ecommerceApplication/src/main/resources/upload/images"+multipartFile.getOriginalFilename());
            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            System.out.println(convertfile.getAbsolutePath());
            fout.write(multipartFile.getBytes());
            fout.close();
        }

        return new ResponseEntity<>("file added", HttpStatus.OK);

    }

    @Override
    public ResponseEntity downloadImage(String fileName, HttpServletRequest request) throws IOException {
        String fileBasePath = "/home/himanshu/Downloads/ecommerceApplication/src/main/resources/upload/";
        Path path = Paths.get(fileBasePath + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
