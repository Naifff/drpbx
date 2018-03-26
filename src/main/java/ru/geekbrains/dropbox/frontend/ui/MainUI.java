package ru.geekbrains.dropbox.frontend.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.dropbox.backend.service.AuthenticationService;
import ru.geekbrains.dropbox.frontend.model.SavedFile;

@SpringUI
public class MainUI extends UI {

@Autowired
    AuthenticationService authentication;

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        Grid<SavedFile> gridFiles = new Grid<>();
        gridFiles.setSizeFull();

        Panel pnlAutheticate = new Panel("Авторизация");
        pnlAutheticate.setSizeUndefined();
        TextField textFieldLogin=new TextField();
        textFieldLogin.setSizeUndefined();
        PasswordField passwordField=new PasswordField();
        passwordField.setSizeUndefined();
        Button btnGo = new Button("Go!",clickEvent -> {
                if (authentication.login(textFieldLogin.getValue(),passwordField.getValue())){
                    pnlAutheticate.setCaption("Heya!");
                }else{pnlAutheticate.setCaption("Bye!");}

        });

        Panel pnlActions = new Panel();
        pnlActions.setSizeUndefined();

        Upload uploadFile = new Upload();
        uploadFile.setButtonCaption("Загрузить");
        Button btnDelete = new Button("Удалить");
        Button btnDownload = new Button("Скачать");

        HorizontalLayout layoutActions = new HorizontalLayout();
        HorizontalLayout autheticate=new HorizontalLayout();

        layoutActions.setSizeUndefined();
        autheticate.setSizeUndefined();

        pnlActions.setContent(layoutActions);
        pnlAutheticate.setContent(autheticate);

        autheticate.addComponents(textFieldLogin, passwordField, btnGo);
        layoutActions.addComponents(uploadFile, btnDelete, btnDownload);
        layoutSource.addComponents(gridFiles, pnlAutheticate, pnlActions);
        this.setContent(layoutSource);
    }
}
