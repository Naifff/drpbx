package ru.geekbrains.dropbox.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("frontFilesService")
public class FilesServiceImpl implements FilesService {
    @Value("${filePath}")
    private String filePath;

    @Autowired
    ru.geekbrains.dropbox.backend.service.FilesService backService;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return backService.getFileOutputStream(fileName);
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(filePath + "\\" + fileName);
    }

    @Override
    public InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File(filePath + "\\" + fileName));
    }
}
