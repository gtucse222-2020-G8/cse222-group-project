package cse222.group8.desktop.controllers;

import cse222.group8.desktop.client.models.Token;
import cse222.group8.desktop.models.AdoptionRequestsPageModel;
import cse222.group8.desktop.models.SettingsPageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SettingsPageController implements PageWithTokenController {
    public Button changeCapacityButton;
    public Button changePasswordButton;
    public Button changeShelterNameButton;
    @FXML
    public LeftMenuPanelController leftMenuController;
    public VBox leftMenu;

    private SettingsPageModel model;

    @FXML
    public void initialize(){
        model = new SettingsPageModel();
        leftMenuController.changeFocus(6);
    }

    @Override
    public void setToken(Token token) {
        model.setToken(token);
        leftMenuController.setToken(token);
    }
}
