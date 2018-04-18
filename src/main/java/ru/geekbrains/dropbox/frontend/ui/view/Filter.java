package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.dropbox.frontend.dao.FilesDAOImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class Filter extends CustomComponent {


    @Autowired
    private FilesDAOImpl filesDAO;

    private List<TextField> textFields = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();

    private Button addBtn = new Button("+");
    private Button applyBtn = new Button("Применить");

    private Panel panel = new Panel("");//Панель для фильтров
    private VerticalLayout panelContent = new VerticalLayout();
    private HorizontalLayout btnLayout = new HorizontalLayout();

    public Filter() {
        addBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                HorizontalLayout layout = new HorizontalLayout();
                Button button = new Button("-");
                TextField textField = new TextField();
                Label label = new Label();

                textFields.add(textField);
                labels.add(label);
                refreshLabels();

                layout.addComponents(label, textField, button);

                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        panelContent.removeComponent(layout);
                        textFields.remove(textField);
                        labels.remove(label);
                        refreshLabels();
                        addBtn.focus();
                    }
                });
                panelContent.addComponents(layout);
                textField.focus();
            }
        });

        btnLayout.addComponents(addBtn, applyBtn);
        panelContent.addComponents(btnLayout);
        panel.setContent(panelContent);
        setCompositionRoot(panel);
    }

    public void apply(Grid<File> fileGrid) {
        applyBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (textFields.size() == 0)
                    fileGrid.setItems(filesDAO.getFileList());
                else
                    fileGrid.setItems(filesDAO.filterList(textFields));
            }
        });
    }

    private void refreshLabels() {
        if (labels.size() > 0) {
            labels.forEach(label1 -> label1.setValue(""));
            labels.get(0).setValue("");
        }
    }

}
