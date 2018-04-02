package ru.geekbrains.dropbox.frontend.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.geekbrains.dropbox.frontend.model.SavedFile;
import ru.geekbrains.dropbox.frontend.service.AuthService;
import ru.geekbrains.dropbox.frontend.service.FilesService;

import java.io.IOException;
import java.io.OutputStream;

@SpringUI
public class MainUI extends UI {

    @Autowired
    AuthService authService;
    @Autowired
    @Qualifier("frontFilesService")
    FilesService filesService;

    private boolean authentication = false;
    private Panel pnlAutheticate;

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        Grid<SavedFile> gridFiles = new Grid<>();
        gridFiles.setSizeFull();

        pnlAutheticate = new Panel();
        pnlAutheticate.setContent(authLayout());
        pnlAutheticate.setSizeUndefined();

        Panel pnlActions = new Panel("Авторизация должна быть тут!");
        pnlActions.setSizeUndefined();

        Upload uploadFile = new Upload();
        uploadFile.setButtonCaption("Загрузить");
        uploadFile.setReceiver(new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String fileName, String mimeType) {
                try {
                    return filesService.getFileOutputStream(fileName);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    Notification.show("Не удалось загрузить файл!").setDelayMsec(1000);
                }
                return null;
            }
        });
        Button btnDelete = new Button("Удалить");
        Button btnDownload = new Button("Скачать");

        HorizontalLayout layoutActions = new HorizontalLayout();
        layoutActions.setSizeUndefined();

        pnlActions.setContent(layoutActions);
        layoutActions.addComponents(uploadFile, btnDelete, btnDownload);
        layoutSource.addComponents(gridFiles, pnlAutheticate, pnlActions);
        this.setContent(layoutSource);
    }

    private void startedUpload(Upload.StartedEvent event) {
        Notification.show("UploadStart");
    }

    private HorizontalLayout authLayout() {
        HorizontalLayout authLayout = new HorizontalLayout();
        TextField login = new TextField();
        login.setPlaceholder("Login");
        PasswordField pass = new PasswordField();
        pass.setPlaceholder("Password");
        Button btnLogin = new Button("Login", clickEvent -> {
            this.authentication = authService.login(login.getValue(), pass.getValue());
            if (this.authentication) {
                pnlAutheticate.setVisible(false);
            }
        });
        authLayout.addComponents(login, pass, btnLogin);
        return authLayout;
    }
}
