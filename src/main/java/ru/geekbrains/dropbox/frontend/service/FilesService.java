package ru.geekbrains.dropbox.frontend.service;

import java.io.*;

public interface FilesService {
    OutputStream getFileOutputStream(String fileName) throws IOException;
    File getFileByName(String fileName);
    InputStream getFileInputStream(String fileName) throws FileNotFoundException;
}
