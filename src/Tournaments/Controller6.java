package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller6 implements Initializable {

    @FXML
    private TextField Tour_Type1;
    @FXML
    private Button generate;
    static boolean x = false;

    @FXML
    private Button delete;

    @FXML
    private TextField matchType;
    @FXML
    private TextField Tournament_ID;

    @FXML
    private TextField type;
    @FXML
    private TextField regEnd;

    @FXML
    private TextField regStart;
    static String modifyName;
    static String modifyID;
    static String modifyType;
    static String modifyTourStart;
    static String modifyTourEnd;
    static String modifyRegStart;
    static String modifyRegEnd;
    static String modifyMatchType;
    static String modifyTour_Type;
    @FXML
    private Button modify;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private TextField endTour;

    public TextField getEndTour() {
        return endTour;
    }

    @FXML
    private TextField startTour;

    public TextField getStartTour() {
        return startTour;
    }

    @FXML
    private TextField Erorr;
    @FXML
    private TextField TournamentName;

    public TextField getTournamentName() {
        return TournamentName;
    }

    @FXML
    private Button registerButton;

    @FXML
    private Button Home;

    @FXML
    private Button Profile;

    @FXML
    private Button Settings;
    @FXML
    private Pane pane;

    @FXML
    private Label label;
    @FXML
    private GridPane h1;

    @FXML
    private GridPane h2;

    @FXML
    private GridPane h3;
    @FXML
    private Button view;
    static boolean isAdmin = false;

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    // }

    @FXML
    void Onklick(ActionEvent e) {
        if (e.getSource() == Home) {
            label.setText("Home");
            h1.toFront();
        } else if (e.getSource() == Profile) {
            label.setText("profile");
            h2.toFront();

        } else {
            if (e.getSource() == Settings) {
                label.setText("settings");
                h3.toFront();
            }
        }
    }

    @FXML
    private TableView<Tournament> Table;

    @FXML
    private TableColumn<Tournament, String> Tour_End;

    @FXML
    private TableColumn<Tournament, String> Tour_Start;

    @FXML
    private TableColumn<Tournament, String> Touranment_Name;

    @FXML
    private TableColumn<Tournament, String> TournamentID;

    @FXML
    private TableColumn<Tournament, String> Type;

    @FXML
    private TableColumn<Tournament, String> match_Type;

    @FXML
    private TableColumn<Tournament, String> reg_end;

    @FXML
    private TableColumn<Tournament, String> reg_start;
    @FXML
    private TableColumn<table2, String> Tour_End1;

    @FXML
    private TableColumn<table2, String> Tour_Start1;

    @FXML
    private TableColumn<table2, String> Touranment_Name1;

    @FXML
    private TableColumn<table2, String> TournamentID1;

    @FXML
    private TableColumn<table2, String> Type1;

    @FXML
    private TableColumn<table2, String> match_Type1;

    @FXML
    private TableColumn<table2, String> reg_end1;

    @FXML
    private TableColumn<table2, String> reg_start1;
    @FXML
    private TableColumn<table2, String> Tour_Type2;
    @FXML
    private TableColumn<Tournament, String> Tour_Type;

    @FXML
    private TableView<table2> Table1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        h1.toFront();

        TournamentID1.setCellValueFactory(new PropertyValueFactory<table2, String>("TournamentID1"));
        reg_start1.setCellValueFactory(new PropertyValueFactory<table2, String>("reg_start1"));
        Tour_Start1.setCellValueFactory(new PropertyValueFactory<table2, String>("Tour_Start1"));
        Tour_End1.setCellValueFactory(new PropertyValueFactory<table2, String>("Tour_End1"));
        reg_end1.setCellValueFactory(new PropertyValueFactory<table2, String>("reg_end1"));
        match_Type1.setCellValueFactory(new PropertyValueFactory<table2, String>("match_Type1"));
        Touranment_Name1.setCellValueFactory(new PropertyValueFactory<table2, String>("Touranment_Name1"));
        Type1.setCellValueFactory(new PropertyValueFactory<table2, String>("Type1"));
        Tour_Type2.setCellValueFactory(new PropertyValueFactory<table2, String>("Tour_Type2"));

        TournamentID.setCellValueFactory(new PropertyValueFactory<Tournament, String>("TournamentID"));
        reg_start.setCellValueFactory(new PropertyValueFactory<Tournament, String>("reg_start"));
        Tour_Start.setCellValueFactory(new PropertyValueFactory<Tournament, String>("Tour_Start"));
        Tour_End.setCellValueFactory(new PropertyValueFactory<Tournament, String>("Tour_End"));
        reg_end.setCellValueFactory(new PropertyValueFactory<Tournament, String>("reg_end"));
        match_Type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("match_Type"));
        Touranment_Name.setCellValueFactory(new PropertyValueFactory<Tournament, String>("Touranment_Name"));
        Type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("Type"));
        Tour_Type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("Tour_Type"));

        if (isAdmin)
            appear();

        setUpTable();

    }

    @FXML
    private Button show;

    @FXML
    void show(ActionEvent e) {
        setUpTable1();
    }

    private void setUpTable() {

        ArrayList<String[]> elemts = Tournament.getarray();
        for (int i = 0; i < elemts.size(); i++) {
            Tournament s1 = new Tournament(elemts.get(i)[0], elemts.get(i)[1],
                    elemts.get(i)[5], elemts.get(i)[6], elemts.get(i)[3],
                    elemts.get(i)[4], elemts.get(i)[7], elemts.get(i)[2], elemts.get(i)[8]);

            Table.getItems().add(s1);
        }
    }

    private void setUpTable1() {

        ArrayList<String[]> elemts = table2.getarray1();
        for (int i = 0; i < elemts.size(); i++) {
            if (Controller1.user.equals(elemts.get(i)[0])) {
                table2 s1 = new table2(elemts.get(i)[1], elemts.get(i)[2],
                        elemts.get(i)[6], elemts.get(i)[7], elemts.get(i)[4],
                        elemts.get(i)[5], elemts.get(i)[3], elemts.get(i)[8],elemts.get(i)[9]);
                Table1.getItems().add(s1);
            }
        }
    }

    @FXML
    public void register(String name, String tourId, String tourNmae, String type, String startTour, String endTour,
            String regStar, String regEnd, String sport, String Tour_Type) throws IOException {
        System.out.println("kkk");
        System.out.println(Tour_Type);
        FileWriter file = new FileWriter(new File("registration.txt"), true);
        file.write(name + "," + tourId + "," + tourNmae + "," + type + "," + startTour + "," + endTour + "," + regStar
                + "," + regEnd + "," + sport +","+ Tour_Type +"\n");
        file.close();
    }

    public void check() {

    }

    public boolean conflictName(String username, String tourName) throws FileNotFoundException {
        ArrayList<String[]> userTours = getUserTours(username);
        for (int i = 0; i < userTours.size(); i++) {
            if (userTours.get(i)[1].equals(tourName)) {
                return true;
            }
        }
        return false;

    }

    public ArrayList<String[]> getUserTours(String username) throws FileNotFoundException {
        ArrayList<String[]> arrayList = new ArrayList<>();
        Scanner input = new Scanner(new File("registration.txt"));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] lineArray = line.split(",", 10);
            if (lineArray[0].equals(username))
                arrayList.add(lineArray);

        }

        return arrayList;

    }

    public boolean conflictTime(String username, String startTour, String endTime) throws FileNotFoundException {
        int startSecond = getSeconds(startTour);
        int endSecond = getSeconds(endTime);

        ArrayList<String[]> userTours = getUserTours(username);
        for (int i = 0; i < userTours.size(); i++) {
            int currentStart = getSeconds(userTours.get(i)[2]);
            int currentEnd = getSeconds(userTours.get(i)[3]);
            if ((currentStart <= endSecond && currentStart >= startSecond)
                    || (currentEnd >= startSecond && currentEnd <= endSecond)
                    || (currentEnd >= endSecond && currentStart >= endSecond)) {
                return true;
            }
        }
        return false;

    }

    public int getSeconds(String time) {
        int hour = Integer.parseInt(time.split("")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        int second = minute * 60 + hour * 60 * 60;
        return second;
    }

    // CourseOffering s = Table.getSelectionModel().getSelectedItem();
    // CourseName.setText(String.valueOf(s.getCourseSection()));
    // Time1.setText(String.valueOf(s.getTime()));
    // Days.setText(String.valueOf(s.getDay()));
    // Activity1.setText(String.valueOf(s.getActivity()));
    @FXML
    void onclick(ActionEvent e) throws IOException {
        if (conflictName(Controller1.user, Tournament_ID.getText())) {
            Erorr.setText("you are already register in this tournament ");
            Erorr.setVisible(true);
            return;
        }

        // if (conflictTime(Controller1.user, startTour.getText(), endTour.getText())) {
        // Erorr.setText("you cannot register due to the conflict time");
        // Erorr.setVisible(true);
        // return;
        // }

        register(Controller1.user, Tournament_ID.getText(), TournamentName.getText(), type.getText(),
                startTour.getText(), endTour.getText(), regStart.getText(), regEnd.getText(), matchType.getText(),
                Tour_Type1.getText());
            System.out.println(Tour_Type1.getText());

        //
    }

    @FXML
    void onSelect(MouseEvent e) {
        Tournament s = Table.getSelectionModel().getSelectedItem();
        TournamentName.setText(String.valueOf(s.getTouranment_Name()));
        startTour.setText(String.valueOf(s.getTour_Start()));
        endTour.setText(String.valueOf(s.getEndDate()));
        Tournament_ID.setText(String.valueOf(s.getTournamentID()));
        regStart.setText(String.valueOf(s.getRegStart()));
        regEnd.setText(String.valueOf(s.getRegEnd()));
        type.setText(String.valueOf(s.getSport()));
        matchType.setText(String.valueOf(s.getMatch_Type()));
        Tour_Type1.setText(String.valueOf(s.getTour_Type()));

    }

    @FXML
    private Button addTour;

    @FXML
    void appear() {
        addTour.setVisible(true);
        modify.setVisible(true);
        generate.setVisible(true);
        // delete.setVisible(true);
    }

    @FXML
    void switchToScene6(ActionEvent e) {
        Parent root;
        try {
            x = false;
            root = FXMLLoader.load(getClass().getResource("AdminPage1.fxml"));
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
    void switchToScene4(ActionEvent e) {
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("matches.fxml"));
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
    void modify(ActionEvent e) {
        Parent root;
        try {
            modifyName = TournamentName.getText();
            modifyID = Tournament_ID.getText();
            modifyTourStart = startTour.getText();
            modifyTourEnd = endTour.getText();
            modifyRegStart = regStart.getText();
            modifyRegEnd = regEnd.getText();
            modifyType = type.getText();
            modifyMatchType = matchType.getText();
            modifyTour_Type = Tour_Type1.getText();

            x = true;

            root = FXMLLoader.load(getClass().getResource("AdminPage1.fxml"));
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
    void cancel(ActionEvent e) throws IOException {
        String s;
        String container = "";
        Scanner file = new Scanner(new File("t.txt"));
        while (file.hasNextLine()) {
            s = file.nextLine();
            if (s.split(",")[0].equals(TournamentID.getText())) {
                continue;
            }
            container += s + "\n";
        }

        FileWriter reserve = new FileWriter(new File("t.txt"));
        reserve.write(container);
        reserve.close();

    }

}
