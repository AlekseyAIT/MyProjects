package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        DataBase dataBaseUser = new DataBase();

        Text upperText = new Text("Вход");
        upperText.setFont(new Font(16));

        Text entranceInAccountIsSuccessful = new Text("Вход в аккаунт выполнен!");

        Text thisUsernameIsAlreadyTakenError = new Text("Данное имя пользователя уже занято!");
        Text someDetailsAreNotWrittenError = new Text("Заполните все поля!");
        Text thisUsernameIsNotExistError = new Text("Такого пользователя не существует!");
        Text passwordIsIncorrect = new Text("Пароль неверен!");

        thisUsernameIsAlreadyTakenError.setFill(Paint.valueOf("RED"));
        someDetailsAreNotWrittenError.setFill(Paint.valueOf("RED"));
        thisUsernameIsNotExistError.setFill(Paint.valueOf("RED"));
        passwordIsIncorrect.setFill(Paint.valueOf("RED"));

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        username.setPromptText("Имя пользователя");
        password.setPromptText("Пароль");

        Button entryButton = new Button("Войти");
        Button registerButton = new Button("Зарегестрироваться");
        Button goToRegistrationButton = new Button("Регистрация");
        Button goBack = new Button("Вернуться");

        AtomicBoolean errorFlagUsername = new AtomicBoolean(false);
        AtomicBoolean errorFlagDetails = new AtomicBoolean(false);
        AtomicBoolean errorFlagThisUsernameIsNotExist = new AtomicBoolean(false);
        AtomicBoolean errorFlagPasswordIsIncorrect = new AtomicBoolean(false);

        VBox pane = new VBox(goBack, thisUsernameIsAlreadyTakenError, someDetailsAreNotWrittenError,
                entranceInAccountIsSuccessful, upperText, username, password, entryButton,
                goToRegistrationButton, thisUsernameIsNotExistError, passwordIsIncorrect);

        pane.getChildren().remove(goBack);
        pane.getChildren().remove(registerButton);
        pane.getChildren().remove(thisUsernameIsAlreadyTakenError);
        pane.getChildren().remove(someDetailsAreNotWrittenError);
        pane.getChildren().remove(entranceInAccountIsSuccessful);
        pane.getChildren().remove(thisUsernameIsNotExistError);
        pane.getChildren().remove(passwordIsIncorrect);

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 60, 0, 60));
        pane.setSpacing(12);

        entryButton.setOnAction(actionEvent -> {
            LinkedList<String[]> list = dataBaseUser.getList();
            boolean flagHelper;

            for (String[] strings : list) {
                if (strings[0].equals(username.getText()) && strings[1].equals(password.getText())) {
                    pane.getChildren().removeAll(thisUsernameIsAlreadyTakenError, someDetailsAreNotWrittenError, passwordIsIncorrect, upperText, username, password, entryButton, goToRegistrationButton);
                    pane.getChildren().add(entranceInAccountIsSuccessful);
                    break;
                } else if (strings[0].equals(username.getText()) && !strings[1].equals(password.getText()) && !password.getText().equals("")) {
                    flagHelper = errorFlagDetails.get();
                    if (flagHelper) {
                        pane.getChildren().remove(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(false);
                    }

                    flagHelper = errorFlagThisUsernameIsNotExist.get();
                    if (flagHelper) {
                        pane.getChildren().remove(thisUsernameIsNotExistError);
                        errorFlagThisUsernameIsNotExist.set(false);
                    }

                    flagHelper = errorFlagPasswordIsIncorrect.get();
                    if (!flagHelper) {
                        pane.getChildren().add(passwordIsIncorrect);
                        errorFlagPasswordIsIncorrect.set(true);
                    }

                    break;
                } else if (username.getText().equals("")
                        || strings[0].equals(username.getText()) && password.getText().equals("")) {
                    flagHelper = errorFlagPasswordIsIncorrect.get();
                    if (flagHelper) {
                        pane.getChildren().remove(passwordIsIncorrect);
                        errorFlagPasswordIsIncorrect.set(false);
                    }

                    flagHelper = errorFlagThisUsernameIsNotExist.get();
                    if (flagHelper) {
                        pane.getChildren().remove(thisUsernameIsNotExistError);
                        errorFlagThisUsernameIsNotExist.set(false);
                    }

                    flagHelper = errorFlagDetails.get();
                    if (!flagHelper) {
                        pane.getChildren().add(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(true);
                    }

                    break;
                } else if (!strings[0].equals(username.getText()) && password.getText().equals("")
                        || !strings[0].equals(username.getText()) && !password.getText().equals("")) {
                    flagHelper = errorFlagPasswordIsIncorrect.get();
                    if (flagHelper) {
                        pane.getChildren().remove(passwordIsIncorrect);
                        errorFlagPasswordIsIncorrect.set(false);
                    }

                    flagHelper = errorFlagDetails.get();
                    if (flagHelper) {
                        pane.getChildren().remove(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(false);
                    }

                    flagHelper = errorFlagThisUsernameIsNotExist.get();
                    if (!flagHelper) {
                        pane.getChildren().add(thisUsernameIsNotExistError);
                        errorFlagThisUsernameIsNotExist.set(true);
                    }
                }
            }
        });

        AtomicInteger counter = new AtomicInteger(0);
        registerButton.setOnAction(actionEvent -> {
            LinkedList<String[]> list = dataBaseUser.getList();
            boolean userIsCorrect = true;
            boolean flagHelper;

            for (String[] strings : list) {
                if (strings[0].equals(username.getText())) {
                    flagHelper = errorFlagDetails.get();
                    if (flagHelper) {
                        pane.getChildren().remove(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(false);
                    }

                    flagHelper = errorFlagUsername.get();
                    if (!flagHelper) {
                        pane.getChildren().add(thisUsernameIsAlreadyTakenError);
                        errorFlagUsername.set(true);
                    }

                    userIsCorrect = false;
                    break;
                }
            }

            if (userIsCorrect) {
                if (!username.getText().equals("") && !password.getText().equals("")) {
                    dataBaseUser.addUser(new String[]{username.getText(), password.getText()});
                    System.out.println("Зарегистрированный пользователь №" + counter.incrementAndGet() + ": " +
                            Arrays.toString(new String[]{username.getText(), password.getText()}));

                    flagHelper = errorFlagDetails.get();
                    if (flagHelper) {
                        pane.getChildren().remove(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(false);
                    }

                    flagHelper = errorFlagUsername.get();
                    if (flagHelper) {
                        pane.getChildren().remove(thisUsernameIsAlreadyTakenError);
                        errorFlagUsername.set(false);
                    }
                } else if (username.getText().equals("") && password.getText().equals("")) {
                    flagHelper = errorFlagUsername.get();
                    if (flagHelper) {
                        pane.getChildren().remove(thisUsernameIsAlreadyTakenError);
                        errorFlagUsername.set(false);
                    }

                    flagHelper = errorFlagDetails.get();
                    if (!flagHelper) {
                        pane.getChildren().add(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(true);
                    }
                } else {
                    flagHelper = errorFlagUsername.get();
                    if (flagHelper) {
                        pane.getChildren().remove(thisUsernameIsAlreadyTakenError);
                        errorFlagUsername.set(false);
                    }

                    flagHelper = errorFlagDetails.get();
                    if (!flagHelper) {
                        pane.getChildren().add(someDetailsAreNotWrittenError);
                        errorFlagDetails.set(true);
                    }
                }
            }
        });

        goToRegistrationButton.setOnAction(actionEvent -> {
            boolean flagHelper;

            upperText.setText("Регистрация");

            pane.getChildren().remove(entryButton);
            pane.getChildren().remove(goToRegistrationButton);

            flagHelper = errorFlagDetails.get();
            if (flagHelper) {
                pane.getChildren().remove(someDetailsAreNotWrittenError);
                errorFlagDetails.set(false);
            }

            flagHelper = errorFlagPasswordIsIncorrect.get();
            if (flagHelper) {
                pane.getChildren().remove(passwordIsIncorrect);
                errorFlagPasswordIsIncorrect.set(false);
            }

            flagHelper = errorFlagThisUsernameIsNotExist.get();
            if (flagHelper) {
                pane.getChildren().remove(thisUsernameIsNotExistError);
                errorFlagThisUsernameIsNotExist.set(false);
            }

            pane.getChildren().add(registerButton);
            pane.getChildren().add(goBack);

            username.setText("");
            password.setText("");
        });

        goBack.setOnAction(actionEvent -> {
            boolean flagHelper;

            upperText.setText("Вход");

            pane.getChildren().remove(goBack);
            pane.getChildren().remove(registerButton);

            flagHelper = errorFlagUsername.get();
            if (flagHelper) {
                pane.getChildren().remove(thisUsernameIsAlreadyTakenError);
                errorFlagUsername.set(false);
            }

            flagHelper = errorFlagDetails.get();
            if (flagHelper) {
                pane.getChildren().remove(someDetailsAreNotWrittenError);
                errorFlagDetails.set(false);
            }

            pane.getChildren().add(entryButton);
            pane.getChildren().add(goToRegistrationButton);

            username.setText("");
            password.setText("");
        });

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("NASA corp.");

        stage.setHeight(300);
        stage.setWidth(300);
        stage.setMinHeight(300);
        stage.setMaxHeight(300);
        stage.setMinWidth(300);
        stage.setMaxWidth(300);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
