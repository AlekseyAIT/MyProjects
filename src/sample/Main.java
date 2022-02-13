package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("Button was clicked 0 times!");

        text.setLayoutX(100);
        text.setLayoutY(48);

        Button button = new Button("Click here!");

        button.setLayoutX(20);
        button.setLayoutY(30);

        AtomicInteger counter = new AtomicInteger(0);
        button.setOnAction(actionEvent -> text.setText("Button was clicked " + counter.incrementAndGet() + " times!"));

        CheckBox checkBox1 = new CheckBox("Java");

        checkBox1.setLayoutX(20);
        checkBox1.setLayoutY(100);

        CheckBox checkBox2 = new CheckBox("C++");

        checkBox2.setLayoutX(20);
        checkBox2.setLayoutY(130);

        CheckBox checkBox3 = new CheckBox("Python");

        checkBox3.setLayoutX(20);
        checkBox3.setLayoutY(160);

        Text text1 = new Text("Nothing is selected!");

        text1.setLayoutX(100);
        text1.setLayoutY(135);

        checkBox1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (checkBox1.isSelected()) {
                    if (!checkBox2.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + " is selected!");
                    } else if (checkBox2.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (!checkBox2.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (checkBox2.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + " is selected!");
                    }
                } else {
                    if (!checkBox2.isSelected() && !checkBox3.isSelected()) {
                        text1.setText("Nothing is selected!");
                    } else if (checkBox2.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (!checkBox2.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox3.getText() + " is selected!");
                    } else if (checkBox2.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox2.getText() + " is selected!");
                    }
                }
            }
        });

        checkBox2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (checkBox2.isSelected()) {
                    if (!checkBox1.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox2.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (!checkBox1.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + " is selected!");
                    }
                } else {
                    if (!checkBox1.isSelected() && !checkBox3.isSelected()) {
                        text1.setText("Nothing is selected!");
                    } else if (checkBox1.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (!checkBox1.isSelected() && checkBox3.isSelected()) {
                        text1.setText(checkBox3.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && !checkBox3.isSelected()) {
                        text1.setText(checkBox1.getText() + " is selected!");
                    }
                }
            }
        });

        checkBox3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (checkBox3.isSelected()) {
                    if (!checkBox1.isSelected() && !checkBox2.isSelected()) {
                        text1.setText(checkBox3.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && checkBox2.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (!checkBox1.isSelected() && checkBox2.isSelected()) {
                        text1.setText(checkBox2.getText() + ", " + checkBox3.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && !checkBox2.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox3.getText() + " is selected!");
                    }
                } else {
                    if (!checkBox1.isSelected() && !checkBox2.isSelected()) {
                        text1.setText("Nothing is selected!");
                    } else if (checkBox1.isSelected() && checkBox2.isSelected()) {
                        text1.setText(checkBox1.getText() + ", " + checkBox2.getText() + " is selected!");
                    } else if (!checkBox1.isSelected() && checkBox2.isSelected()) {
                        text1.setText(checkBox2.getText() + " is selected!");
                    } else if (checkBox1.isSelected() && !checkBox2.isSelected()) {
                        text1.setText(checkBox1.getText() + " is selected!");
                    }
                }
            }
        });

        Group group = new Group();
        group.getChildren().add(text);
        group.getChildren().add(button);
        group.getChildren().add(checkBox1);
        group.getChildren().add(checkBox2);
        group.getChildren().add(checkBox3);
        group.getChildren().add(text1);

        Scene scene = new Scene(group);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Application");
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.setMaxWidth(300);
        primaryStage.setMinWidth(300);
        primaryStage.setMaxHeight(300);
        primaryStage.setMinHeight(300);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
