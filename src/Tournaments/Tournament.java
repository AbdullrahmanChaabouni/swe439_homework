package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tournament {
    private String Tour_Type;

    public String getTour_Type() {
        return Tour_Type;
    }


    private String TournamentID;
    public String getTournamentID() {
        return TournamentID;
    }


    private String Touranment_Name;
    public String getTouranment_Name() {
        return Touranment_Name;
    }


    private String reg_start;
    public String getReg_start() {
        return reg_start;
    }


    private String reg_end;
    public String getReg_end() {
        return reg_end;
    }


    private String Tour_Start;
    public String getTour_Start() {
        return Tour_Start;
    }


    private String Tour_End;
    public String getTour_End() {
        return Tour_End;
    }


    private String Type;
    public String getType() {
        return Type;
    }


    private String match_Type;

    public String getMatch_Type() {
        return match_Type;
    }
 

    public Tournament(String[] getarray) {
    }

    public Tournament(Object o) {
    }

    public Tournament(String TournamentID, String Touranment_Name, String reg_start, String reg_end, String Tour_Start,
            String Tour_End, String Type, String match_Type ,String Tour_Type) {
        this.TournamentID = TournamentID;
        this.Touranment_Name = Touranment_Name;
        this.reg_start = reg_start;
        this.reg_end = reg_end;
        this.Tour_Start = Tour_Start;
        this.Tour_End = Tour_End;
        this.Type = Type;
        this.match_Type = match_Type;
        this.Tour_Type = Tour_Type;

    }
    // public Tournament(String TournamentID1, String Touranment_Name1, String reg_start1, String reg_end1, String Tour_Start1,
    //         String Tour_End1, String Type1, String match_Type1) {
    //     this.TournamentID1 = TournamentID1;
    //     this.Touranment_Name1 = Touranment_Name1;
    //     this.reg_start1 = reg_start1;
    //     this.reg_end1 = reg_end1;
    //     this.Tour_Start1 = Tour_Start1;
    //     this.Tour_End1 = Tour_End1;
    //     this.Type1 = Type1;
    //     this.match_Type1 = match_Type1;

    // }

    public boolean generateMatches() {
        return true;
    }

    public boolean getRanking() {
        return true;
    }

    public String getID() {
        return TournamentID;
    }

    public String getName() {
        return Touranment_Name;
    }

    public String getRegStart() {
        return reg_start;
    }

    public String getRegEnd() {
        return reg_end;
    }

    public String getStartDate() {
        return Tour_Start;
    }

    public String getEndDate() {
        return Tour_End;
    }

    public String getTourType() {
        return Type;
    }

    public String getSport() {
        return match_Type;
    }

    

    public static ArrayList<String[]>  getarray() {
        ArrayList<String[]> array = new ArrayList<>();
        File f = new File("t.txt");
        try (Scanner input = new Scanner(f)) {
            while(input.hasNextLine()){
                
                String line = input.nextLine();
                String [] lineArray = line.split(",", 9);
                array.add(lineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }
    // public static ArrayList<String[]>  getarray1() {
    //     ArrayList<String[]> array = new ArrayList<>();
    //     File f = new File("t.txt");
    //     try (Scanner input = new Scanner(f)) {
    //         while(input.hasNextLine()){
                
    //             String line = input.nextLine();
    //             String [] lineArray = line.split(",", 8);
    //             array.add(lineArray);
    //         }
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //     return array;
    // }


    public void saveTour() throws IOException {
        FileWriter file = new FileWriter(new File("t.txt"), true);
        file.write(
            this.TournamentID + ',' +
                        this.Touranment_Name + ',' +
                        this.Type+ ',' +
                        this.Tour_Start + ',' +
                        this.Tour_End + ',' +
                        this.reg_start + ',' +
                        this.reg_end + ',' +
                        this.match_Type +","+this.Tour_Type+ "\n");
                        file.close();
                        
    }
     public void delete() throws IOException{
        String s;
        String container = "";
        Scanner file = new Scanner(new File("t.txt"));
        while (file.hasNextLine()) {
            s = file.nextLine();
            if (s.split(",")[0].equals(this.TournamentID)) {
                continue;
            }
            container += s + "\n";
        }
        FileWriter reserve = new FileWriter(new File("t.txt"));
        reserve.write(container);
        reserve.close();
     }

     public void modifyTournament() throws IOException{
        Tournament modify = new Tournament(TournamentID, Touranment_Name, reg_start, reg_end, Tour_Start, Tour_End, Type, match_Type,Tour_Type);
        modify.delete();
        modify.saveTour();
     }

}