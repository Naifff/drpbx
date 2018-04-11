package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.dropbox.frontend.model.SavedFile;
import ru.geekbrains.dropbox.frontend.ui.MainUI;

import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginView extends VerticalLayout implements View {


    @Autowired
private AuthenticationManager authenticationManager = null;


    public LoginView() {
        Label label = new Label("LoginView.class");
        addComponent(label);

        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();
        addComponent(layoutSource);


        Panel pnlAutheticate = new Panel("Авторизация");
        pnlAutheticate.setSizeUndefined();
        TextField textFieldLogin=new TextField();
        textFieldLogin.setSizeUndefined();
        PasswordField passwordField=new PasswordField();
        passwordField.setSizeUndefined();
        Button btnGo = new Button("Go!",clickEvent -> {
            if (login(textFieldLogin.getValue(),passwordField.getValue())){
                pnlAutheticate.setCaption("Heya!");
                              MainUI.getCurrent().getNavigator().navigateTo("/");
            }else{pnlAutheticate.setCaption("Bye!");}

        });


        HorizontalLayout layoutActions = new HorizontalLayout();
        HorizontalLayout autheticate=new HorizontalLayout();

        layoutActions.setSizeUndefined();
        autheticate.setSizeUndefined();

        pnlAutheticate.setContent(autheticate);

        autheticate.addComponents(textFieldLogin, passwordField, btnGo);
        layoutSource.addComponents(pnlAutheticate);

    }



    public boolean login(String login,String pass) {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(login,
                    pass);

            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);
        } catch (AuthenticationException e) {

            return false;
        }

        return true;
    }

}