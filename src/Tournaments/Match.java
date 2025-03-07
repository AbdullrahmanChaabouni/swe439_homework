package Tournaments;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Match {
    private String TournamentID1;
    private String player1;
    private String player2;
    private String tourDate;

    public String getTourDate() {
        return tourDate;
    }

    private String matchID;

    public String getMatchID() {
        return matchID;
    }

    private String FirstScore;

    public String getFirstScore() {
        return FirstScore;
    }

    private String SecondScore;

    public String getSecondScore() {
        return SecondScore;
    }

    private String round;

    public String getRound() {
        return round;
    }

    public String getTournamentID1() {
        return TournamentID1;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Match(String TournamentID1, String player1, String player2, String tourDate,
            String FirstScore,
            String SecondScore, String round) {
        this.TournamentID1 = TournamentID1;
        this.player1 = player1;
        this.player2 = player2;
        this.tourDate = tourDate;
        
        this.FirstScore = FirstScore;
        this.SecondScore = SecondScore;
        this.round = round;
    }

    public void saveMatch() throws IOException {
        FileWriter file = new FileWriter(new File("Matches.txt."), true);
        // String scoresData = "";

        // for (int i = 0; i < this.score.length; i++) {
        // scoresData = scoresData + this.score[i] + ",";
        // }

        String data = this.TournamentID1 + "," + this.matchID + "," + this.player1 + "," + this.player2 + ","
                + this.tourDate + "," + this.FirstScore + "," + this.SecondScore + "," + this.round + "\n";
        file.write(data);
        file.close();

    }

    public void saveTour() throws IOException {
        FileWriter file = new FileWriter(new File("Matches.txt"), true);
        file.write(
                this.TournamentID1 + ',' +
                        this.matchID + ',' +
                        this.player1 + ',' +
                        this.player2 + ',' +
                        this.tourDate + ',' +
                        this.FirstScore + ',' +
                        this.SecondScore + ',' +
                        this.round + "\n");
        file.close();

    }

    public void delete() throws IOException {
        String s;
        String container = "";
        Scanner file = new Scanner(new File("Matches.txt"));
        while (file.hasNextLine()) {
            s = file.nextLine();
            if (s.split(",")[7].equals(this.round)&&s.split(",")[2].equals(this.player1)) {
                continue;
            }
            container += s + "\n";
        }
        FileWriter reserve = new FileWriter(new File("Matches.txt"));
        reserve.write(container);
        reserve.close();
    }

}
