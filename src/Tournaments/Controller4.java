package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.Action;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

//import Tableview.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controller4 implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Button add;
    @FXML
    private TextField Second_Score;
    @FXML
    private TextField First_Score;
    @FXML
    private Button Generate_Matches;
    @FXML
    private TextField matchId;
    @FXML
    private TextField MatchID;

    @FXML
    private TableColumn<Match, String> FirstScore;

    @FXML
    private TableColumn<Match, String> SecondScore;

    @FXML
    private TableView<Match> Table1;

    @FXML
    private TableColumn<Match, String> TournamentID1;

    @FXML
    private TableColumn<Match, String> matchID;

    @FXML
    private TableColumn<Match, String> player1;

    @FXML
    private TableColumn<Match, String> player2;

    @FXML
    private TableColumn<Match, String> round;

    @FXML
    private TableColumn<Match, String> tourDate;
    @FXML
    private TextField TournamentID;
    @FXML
    private Button update;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TournamentID1.setCellValueFactory(new PropertyValueFactory<Match, String>("TournamentID1"));
        //matchID.setCellValueFactory(new PropertyValueFactory<Match, String>("matchID"));
        player1.setCellValueFactory(new PropertyValueFactory<Match, String>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<Match, String>("player2"));
        tourDate.setCellValueFactory(new PropertyValueFactory<Match, String>("tourDate"));
        FirstScore.setCellValueFactory(new PropertyValueFactory<Match, String>("FirstScore"));
        SecondScore.setCellValueFactory(new PropertyValueFactory<Match, String>("SecondScore"));
        round.setCellValueFactory(new PropertyValueFactory<Match, String>("round"));
        setUpTable();
    }

    private void setUpTable() {
        ArrayList<String[]> elemts = getarray();
        for (int i = 0; i < elemts.size(); i++) {
            Match s1 = new Match(elemts.get(i)[0], elemts.get(i)[2],
                    elemts.get(i)[3], elemts.get(i)[4],
                    elemts.get(i)[5], elemts.get(i)[6], elemts.get(i)[7]);

            Table1.getItems().add(s1);
        }
    }

    public static ArrayList<String[]> getarray() {

        ArrayList<String[]> array = new ArrayList<>();
        File f = new File("Matches.txt");
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {

                String line = input.nextLine();
                String[] lineArray = line.split(",", 8);
                array.add(lineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }

    @FXML
    void generate(ActionEvent e) throws IOException {
        String ID = TournamentID.getText();
        Tournament1.testingRoundRobinGenerator(ID);
        
    }

    @FXML
    private TextField match_Id;

    @FXML
    private TextField player_1;

    @FXML
    private TextField player_2;

    @FXML
    private TextField round_;

    @FXML
    private TextField tour_Date;

    @FXML
    void add(ActionEvent e) throws IOException {
        Match match = new Match(TournamentID.getText(), player_1.getText(), player_2.getText(), tour_Date.getText(),
                 First_Score.getText(), Second_Score.getText(), round_.getText());
        match.delete();
        match.saveTour();
    
    }

    @FXML
    void onSelect(MouseEvent e) {
        Match s = Table1.getSelectionModel().getSelectedItem();
        TournamentID.setText(String.valueOf(s.getTournamentID1()));
        player_1.setText(String.valueOf(s.getPlayer1()));
        player_2.setText(String.valueOf(s.getPlayer2()));
        tour_Date.setText(String.valueOf(s.getTourDate()));
        //match_Id.setText(String.valueOf(s.getMatchID()));
        First_Score.setText(String.valueOf(s.getFirstScore()));
        Second_Score.setText(String.valueOf(s.getSecondScore()));
        round_.setText(String.valueOf(s.getRound()));

    }

    @FXML
    void switchToScene6(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("f.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    @FXML
    void switchToScene5(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("update.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}