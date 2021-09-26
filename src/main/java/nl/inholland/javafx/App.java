package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Inholland JavaFX Starter Project");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        Label userLabel = new Label("Username: ");
        GridPane.setConstraints(userLabel, 0, 0);

        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel, 0, 1);

        Label visiblePasswordLabel = new Label();
        GridPane.setConstraints(visiblePasswordLabel, 0, 5);

        TextField userInput = new TextField();
        System.out.println(userInput.getText());
        userInput.setPromptText("Username");
        GridPane.setConstraints(userInput, 1, 0);

        PasswordField passwordField = new PasswordField();
        System.out.println(passwordField.getText());
        userInput.setPromptText("Password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 2);

        gridPane.getChildren().addAll(userLabel, passwordLabel,
                visiblePasswordLabel, userInput, passwordField, loginButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        loginButton.setVisible(false);
        StringProperty passwordFieldProperty = passwordField.textProperty();
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {
                loginButton.setVisible(isValidPassword(newValue));
            }
        });

        visiblePasswordLabel.textProperty().bind(passwordFieldProperty);

        window.show();
    }

    public boolean isValidPassword(String password){
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        boolean containsSpecialCharacter = matcher.find();

        return password.matches(".*\\d.*") && containsSpecialCharacter && password.length() >= 8;
    }
}
