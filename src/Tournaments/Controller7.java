package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.Node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller7 implements Initializable {
    @FXML
    private DatePicker Date1;

    @FXML
    private DatePicker Date2;

    @FXML
    private DatePicker Date3;
    

    @FXML
    private DatePicker Date;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private Button back;
    @FXML
    private TextField sportText;
    @FXML
    private Button addSport;
    @FXML
    private Button deleteSport;
    @FXML
    private ComboBox<String> com;
    @FXML
    private ComboBox<String> com1;
    @FXML
    private ComboBox<String> com2;

    @FXML
    private TextField Erorr;
    @FXML
    private Button dekete;
    @FXML
    private Button modify;

    static Tournament modified;
    // @FXML
    // private Button modify;

    @FXML
    private TextField Tour_End;

    @FXML
    private TextField Tour_Start;

    @FXML
    private TextField Touranment_Name;

    @FXML
    private TextField TournamentID;

    @FXML
    private TextField Type;

    @FXML
    private Button add;

    @FXML
    private TextField match_Type;

    @FXML
    private TextField reg_end;

    @FXML
    private TextField reg_start;
    @FXML
    private TextField Erorr1;

    @FXML
    void addTour(ActionEvent e) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Erorr.setVisible(false);
        if (Check()) {
            if(sdf.parse(reg_start.getText()).after(sdf.parse(Tour_Start.getText())) || sdf.parse(reg_start.getText()).after(sdf.parse(Tour_End.getText())) || sdf.parse(reg_end.getText()).after(sdf.parse(Tour_Start.getText())) || sdf.parse(reg_end.getText()).after(sdf.parse(Tour_End.getText())) || sdf.parse(reg_start.getText()).after(sdf.parse(reg_end.getText())) ||(sdf.parse(Tour_Start.getText())).after(sdf.parse(Tour_End.getText()))){
                Erorr1.setVisible(true);
            }
            else{
            write();
                
            }
        } else {
            Erorr.setVisible(true);
        }
    }

    @FXML
    void modify() {
        TournamentID.setText(Controller6.modifyID);
        Touranment_Name.setText(Controller6.modifyName);
        Tour_Start.setText(Controller6.modifyTourStart);
        Tour_End.setText(Controller6.modifyTourEnd);
        reg_start.setText(Controller6.modifyRegStart);
        reg_end.setText(Controller6.modifyRegEnd);
        com1.setValue(Controller6.modifyType);
        com.setValue(Controller6.modifyMatchType);
        com2.setValue(Controller6.modifyTour_Type);
    }

    ArrayList<String> dropDownList = new ArrayList<>();
    ArrayList<String> dropDownList1 = new ArrayList<>();
    ArrayList<String> dropDownList2 = new ArrayList<>();

    @FXML
    void sportAdd(ActionEvent e) {
        dropDownList.add(sportText.getText());
        com.setItems(FXCollections.observableArrayList(dropDownList));
    }

    @FXML
    void sportDelete(ActionEvent e) {
        dropDownList.remove(sportText.getText());
        com.setItems(FXCollections.observableArrayList(dropDownList));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dropDownList.add("Football");
        dropDownList.add("Volleyball");
        dropDownList.add("Basketball");
        dropDownList.add("Tennis");
        dropDownList1.add("teams");
        dropDownList1.add("individuals");
        dropDownList2.add("Elimination tournaments");
        dropDownList2.add("Round robin tournament");
        
        ObservableList<String> list = FXCollections.observableArrayList(dropDownList);
        ObservableList<String> list1 = FXCollections.observableArrayList(dropDownList1);
        ObservableList<String> list2 = FXCollections.observableArrayList(dropDownList2);
        com.setItems(list);
        com1.setItems(list1);
        com2.setItems(list2);
        modify();
        
        if (Controller6.x) {
            add.setVisible(false);
            TournamentID.setVisible(false);
            Touranment_Name.setVisible(false);
        }
        if (!Controller6.x) {
            dekete.setVisible(false);
            modify.setVisible(false);
        }
    }

    @FXML
    void cancel(ActionEvent e) throws IOException {
        Tournament add = new Tournament(TournamentID.getText(), Touranment_Name.getText(), reg_start.getText(),
                reg_end.getText(), Tour_Start.getText(), Tour_End.getText(), com.getValue(), com1.getValue(),com2.getValue());
        add.delete();

    }

    public Boolean Check() {

        try (Scanner input = new Scanner(new File("t.txt"))) {
            String s;
            while (input.hasNextLine()) {
                s = input.nextLine();
                String[] array = s.split(",", 9);
                if (array[0].equals(TournamentID.getText()) || array[1].equals(Touranment_Name.getText())) {

                    return false;

                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    @FXML
    void modifyTournament(ActionEvent e) throws IOException, ParseException {
        Tournament add = new Tournament(Controller6.modifyID, Touranment_Name.getText(), reg_start.getText(),
                reg_end.getText(), Tour_Start.getText(), Tour_End.getText(), com.getValue(), com1.getValue(),com2.getValue());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (sdf.parse(reg_start.getText()).after(sdf.parse(Tour_Start.getText()))
                || sdf.parse(reg_start.getText()).after(sdf.parse(Tour_End.getText()))
                || sdf.parse(reg_end.getText()).after(sdf.parse(Tour_Start.getText()))
                || sdf.parse(reg_end.getText()).after(sdf.parse(Tour_End.getText()))
                || sdf.parse(reg_start.getText()).after(sdf.parse(reg_end.getText()))
                || (sdf.parse(Tour_Start.getText())).after(sdf.parse(Tour_End.getText()))) {
            Erorr1.setVisible(true);
        }
        add.modifyTournament();

    }

    public void write() throws IOException {
        Tournament add = new Tournament(TournamentID.getText(), Touranment_Name.getText(), reg_start.getText(),
                reg_end.getText(), Tour_Start.getText(), Tour_End.getText(), com.getValue(), com1.getValue(),com2.getValue());
        add.saveTour();

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
    void getDate(ActionEvent e) {
        LocalDate myDate = Date.getValue();
        String myDateformated = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Tour_Start.setText(myDateformated);
    }

    @FXML
    void getDate1(ActionEvent e) {
        LocalDate myDate = Date1.getValue();
        String myDateformated = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Tour_End.setText(myDateformated);
    }

    @FXML
    void getDate2(ActionEvent e) {
        LocalDate myDate = Date2.getValue();
        String myDateformated = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        reg_start.setText(myDateformated);
    }

    @FXML
    void getDate3(ActionEvent e) {
        LocalDate myDate = Date3.getValue();
        String myDateformated = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        reg_end.setText(myDateformated);
    }

    public boolean checkDate(Date startTour, Date endTour, Date regStart, Date regEnd) {
        if (regStart.after(startTour) || regStart.after(endTour) || regEnd.after(startTour) || regEnd.after(endTour)
                || regStart.after(regEnd) || startTour.after(endTour)) {
            return false;
        } else {
            return true;
        }

    }

}
