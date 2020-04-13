package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.UploadDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Customer;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UploadDaoImpl implements UploadDao {
    String firstPath = System.getProperty("user.dir");

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    @Override
    public ResponseEntity<Object> uploadSingleImageForProductVariation(MultipartFile file, ProductVariation productVariation) throws IOException {

        File convertfile = new File(firstPath+"/src/main/resources/productVariation/images" + file.getOriginalFilename());
        convertfile.createNewFile();
        String fileBasePath = firstPath+"/src/main/resources/productVariation/";
        Path path = Paths.get(fileBasePath + convertfile.getName());
        FileOutputStream fout = new FileOutputStream(convertfile);
        System.out.println(convertfile.getAbsolutePath());
        fout.write(file.getBytes());
        fout.close();
        Optional<String> ext = getExtensionByStringHandling(convertfile.getName());
        int count = 0;
        File dir = new File(fileBasePath);

        if (ext.isPresent()) {
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File file1 : files) {
                    String value = productVariation.getId().toString();
                    if (file1.getName().startsWith(value)) {
                        count++;
                        System.out.println(count);
                    }
                }
                String value1 = productVariation.getId().toString();
                value1 = value1 + count;
                Files.move(path, path.resolveSibling(value1 + "." + ext.get()));
            }
        } else {
            throw new RuntimeException();
        }
        return new ResponseEntity<>("file added", HttpStatus.OK);
    }

    public ResponseEntity<Object> uploadSingleImage(MultipartFile file, Customer customer) throws IOException {
        File convertfile = new File(firstPath+"/src/main/resources/users/images" + file.getOriginalFilename());
        convertfile.createNewFile();
        String fileBasePath = firstPath+"/src/main/resources/users/";
        Path path = Paths.get(fileBasePath + convertfile.getName());
        FileOutputStream fout = new FileOutputStream(convertfile);
        fout.write(file.getBytes());
        fout.close();
        Optional<String> ext = getExtensionByStringHandling(convertfile.getName());
        File dir = new File(fileBasePath);
        if (ext.isPresent())
        {
            if (dir.isDirectory())
            {
                File[] files = dir.listFiles();
                for (File file1 : files) {
                    String value = customer.getId().toString();
                    if (file1.getName().startsWith(value)) {
                        Files.delete(Paths.get(file1.getPath()));
                    }
                }
                Files.move(path, path.resolveSibling(customer.getId() + "." + ext.get()));
            }


        } else {
            throw new RuntimeException();
        }
        return new ResponseEntity<>("file added", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> uploadMultipleFiles(MultipartFile[] files) throws IOException {
        for (MultipartFile multipartFile : files) {
            File convertfile = new File("/home/himanshu/BOOTCAMP/ecommerceApplication/src/main/resources/upload/images" + multipartFile.getOriginalFilename());
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
        String fileBasePath = firstPath+"/src/main/resources/users/";
        File dir = new File(fileBasePath);
        Resource resource = null;
        String contentType = null;
        if (dir.isDirectory()) {
            File arr[] = dir.listFiles();
            for (File file : arr) {
                if (file.getName().startsWith(fileName)) {
                    Path path = Paths.get(fileBasePath + file.getName());
                    try
                    {
                        resource = new UrlResource(path.toUri());
                    } catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                    }
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                    System.out.println(contentType);
                }
            }
        }
        System.out.println(contentType);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
