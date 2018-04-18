package ru.geekbrains.dropbox.frontend.dao;

import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FilesDAOImpl implements FilesDAO {
    @Value("${filePath}")
    private String filePath;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        File file = new File(getUserDirectory() + fileName);
        return new FileOutputStream(file);
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(getUserDirectory() + fileName);
    }

    @Override
    public InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File(getUserDirectory() + fileName));
    }

    @Override
    public List<File> getFileList() {
        System.out.println(getUserDirectory());
        List<File> fileList = new ArrayList<>();
        try {
            File folder = new File(getUserDirectory());
            return Arrays.stream(
                        folder
                            .listFiles()
                    )
                    .filter(x -> !x.isDirectory())
                    .collect(Collectors.toList());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public void removeFile(File file) {
        file.delete();
    }

    private String getUserDirectory() {
        String userPath =
                filePath                         // директория общая
                        + File.separator         // разделитель для ОС
                        + SecurityContextHolder  // Контекст секюрити
                            .getContext()        // Получаем контекст
                            .getAuthentication() // Получаем авторизацию текущего пользователя
                            .getName()           // Имя текущего пользователя
                        + File.separator;        // разделитель для ОС
        File userFiles = new File(userPath);
        if (!userFiles.exists())
            userFiles.mkdirs();
        return userPath;
    }


    public List<File> filterList(List<TextField> textFields) {
        Set<File> set = new HashSet<>();
        textFields.forEach(textField ->
                set.addAll(getFileList()
                        .stream()
                        .filter(file -> file.getName()
                                .contains(textField.getValue()))
                        .collect(Collectors.toList())));
        List<File> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

}
