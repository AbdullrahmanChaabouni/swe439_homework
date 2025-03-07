package Tournaments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller1 {
    static String user;
    static String pass;

    @FXML
    private TextField Error;
    @FXML
    private Button Login;

    @FXML
    private TextField Password;

    @FXML
    private Button SignUP;

    @FXML
    private TextField UserName;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private CheckBox check;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void switchToScene2(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SignupPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    // public Boolean Check() {
    //     try (Scanner input = new Scanner(new File("TournamentUseres.txt"))) {
    //         String s;
    //         while (input.hasNextLine()) {
    //             s = input.nextLine();
    //             String[] array = s.split(",", 3);
    //             if (array[2].equals(passwordField.getText()) && array[0].equals(UserName.getText())) {
    //                 return true;
    //             }
    //         }
    //     } catch (FileNotFoundException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     return false;
    // }

    public HttpURLConnection signIn(String username, String password) {
        try {
            URL url = new URL("https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + username
                    + "&password=" + password);

            // Create an HttpURLConnection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            return conn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Login(ActionEvent e) {

        Parent root;
        try {
            String username = UserName.getText();
            String password = passwordField.getText();
            HttpURLConnection res = signIn(username, password);
            System.out.println(res.getResponseCode());

            if (res.getResponseCode() != 200) {
                Error.setVisible(true);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(res.getInputStream()));
            if (br.readLine().contains("admin")) {
                System.out.println("isadmin");
                Controller6.isAdmin = true;
            }
            user = username;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("f.fxml"));
            root = fxmlLoader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            user = username;
            pass = password;

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    void CheckPassword(ActionEvent e) {
        if (check.isSelected()) {
            Password.setText(passwordField.getText());
            Password.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(Password.getText());
        Password.setVisible(false);
        passwordField.setVisible(true);
    }

}
