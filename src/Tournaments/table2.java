package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.Scanner;



public class table2 {
    private String Tour_Type2;
    public String getTour_Type2() {
        return Tour_Type2;
    }
    private String match_Type1;
    public String getMatch_Type1() {
        return match_Type1;
    }
    private String Type1;
    public String getType1() {
        return Type1;
    }
    private String Tour_End1;
    public String getTour_End1() {
        return Tour_End1;
    }
    private String Tour_Start1;
    public String getTour_Start1() {
        return Tour_Start1;
    }
    private String reg_end1;
    public String getReg_end1() {
        return reg_end1;
    }
    private String reg_start1;
    public String getReg_start1() {
        return reg_start1;
    }
    private String Touranment_Name1;
    public String getTouranment_Name1() {
        return Touranment_Name1;
    }
    private String TournamentID1;
    public String getTournamentID1() {
        return TournamentID1;
    }

    public table2(String TournamentID1, String Touranment_Name1, String reg_start1, String reg_end1, String Tour_Start1,
    String Tour_End1, String Type1, String match_Type1,String Tour_Type2 ) {
this.TournamentID1 = TournamentID1;
this.Touranment_Name1 = Touranment_Name1;
this.reg_start1 = reg_start1;
this.reg_end1 = reg_end1;
this.Tour_Start1 = Tour_Start1;
this.Tour_End1 = Tour_End1;
this.Type1 = Type1;
this.match_Type1 = match_Type1;
this.Tour_Type2 = Tour_Type2;

}
    public static ArrayList<String[]>  getarray1() {
        ArrayList<String[]> array = new ArrayList<>();
        File f = new File("registration.txt");
        try (Scanner input = new Scanner(f)) {
            while(input.hasNextLine()){
                
                String line = input.nextLine();
                String [] lineArray = line.split(",", 10);
                array.add(lineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }
}
