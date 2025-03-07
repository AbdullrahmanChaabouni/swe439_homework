package Tournaments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Tournament1 {

    private String ID;
    private String name;
    private String regStart;
    private String regEnd;
    private String startDate;
    private String endDate;
    private String tourType;
    private String sport;
    private String[] members;

    public static void main(String[] args) throws IOException {
        testingEliminationTournament();
        // testingRoundRobinTournament();
        //testingRoundRobinGenerator("69");

    }

    public static void testingRoundRobinGenerator(String ID) throws IOException {
        // read reg file and find members using ID
        ArrayList<String> teams = new ArrayList<>();
        File f = new File("registration.txt");
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {

                String line = input.nextLine();
                String[] lineArray = line.split(",", 9);
                if (ID.equals(lineArray[1])) {
                    teams.add(lineArray[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(teams);

        // ArrayList<String> teams = new ArrayList<>(Arrays.asList(tournament.getMembers()));

        // generate the matches

        int n = 10000;
        int numberOfRounds = teams.size() - 1;
        int numberOfMatchesPerRound = teams.size() / 2;
        double[] scores = new double[2];
        for (int round = 0; round < numberOfRounds; round++) {
            System.out.println("Round " + (round + 1) + ":");
            for (int i = 0; i < numberOfMatchesPerRound; i++) {
                int team1Index = i;
                int team2Index = teams.size() - 1 - i;
                scores[0] = 0;
                scores[1] = 0;
                n--;
                Match match = new Match(ID,  teams.get(team2Index), "2023/5/13", String.valueOf(n), "0","0",
                        String.valueOf(round + 1));
                match.saveMatch();
                System.out.println(teams.get(team1Index) + " vs. " + teams.get(team2Index));
            }
            Collections.rotate(teams.subList(1, teams.size()), 1);
        }

    }

    public static void testingRoundRobinTournament() throws IOException {
        Tournament1 tournament = readTour("69");
        ArrayList<String> teams = new ArrayList<>(Arrays.asList(tournament.getMembers()));

        // Create a map to store the teams' points and other statistics
        Map<String, Integer> points = new HashMap<>();
        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> draws = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Integer> goalsFor = new HashMap<>();
        Map<String, Integer> goalsAgainst = new HashMap<>();

        // Initialize the teams' statistics
        for (String team : teams) {
            points.put(team, 0);
            wins.put(team, 0);
            draws.put(team, 0);
            losses.put(team, 0);
            goalsFor.put(team, 0);
            goalsAgainst.put(team, 0);
        }

        // Conduct the tournament matches
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                String team1 = teams.get(i);
                String team2 = teams.get(j);
                int score1 = (int) (Math.random() * 4);
                int score2 = (int) (Math.random() * 4);
                if (score1 > score2) {
                    points.put(team1, points.get(team1) + 3);
                    wins.put(team1, wins.get(team1) + 1);
                    losses.put(team2, losses.get(team2) + 1);
                } else if (score1 < score2) {
                    points.put(team2, points.get(team2) + 3);
                    wins.put(team2, wins.get(team2) + 1);
                    losses.put(team1, losses.get(team1) + 1);
                } else {
                    points.put(team1, points.get(team1) + 1);
                    points.put(team2, points.get(team2) + 1);
                    draws.put(team1, draws.get(team1) + 1);
                    draws.put(team2, draws.get(team2) + 1);
                }
                goalsFor.put(team1, goalsFor.get(team1) + score1);
                goalsFor.put(team2, goalsFor.get(team2) + score2);
                goalsAgainst.put(team1, goalsAgainst.get(team1) + score2);
                goalsAgainst.put(team2, goalsAgainst.get(team2) + score1);
            }
        }

        // Display the teams' statistics
        System.out.println("Team\t\tPoints\tWins\tDraws\tLosses\tGoals For\tGoals Against");
        for (String team : teams) {
            System.out.println(team + "\t\t" + points.get(team) + "\t" + wins.get(team) + "\t\t" + draws.get(team)
                    + "\t\t" + losses.get(team) + "\t\t" + goalsFor.get(team) + "\t\t\t" + goalsAgainst.get(team));
        }

        // Determine the winner of the tournament
        String winner = determineWinner(points, wins, draws, losses, goalsFor, goalsAgainst);
        System.out.println("Winner: " + winner);
    }

    public static String determineWinner(Map<String, Integer> points, Map<String, Integer> wins,
            Map<String, Integer> draws,
            Map<String, Integer> losses, Map<String, Integer> goalsFor, Map<String, Integer> goalsAgainst) {
        // Create a list of the teams
        List<String> teams = new ArrayList<>(points.keySet());

        // Sort the teams by their points
        Collections.sort(teams, new Comparator<String>() {
            @Override
            public int compare(String team1, String team2) {
                if (points.get(team1) != points.get(team2)) {
                    return points.get(team2) - points.get(team1);
                } else {
                    // If two or more teams have the same number of points,
                    // look at their direct matches to break the tie
                    List<String> teamsToCompare = new ArrayList<>();
                    teamsToCompare.add(team1);
                    teamsToCompare.add(team2);
                    for (String team : teams) {
                        if (!teamsToCompare.contains(team)) {
                            if (wins.get(team1) == wins.get(team) && wins.get(team2) == wins.get(team)) {
                                // If three or more teams have the same number of points
                                // and the same number of wins in their direct matches,
                                // compare their goals scored and conceded to break the tie
                                if ((goalsFor.get(team1) - goalsAgainst.get(team1)) == (goalsFor.get(team)
                                        - goalsAgainst.get(team))) {
                                    return goalsFor.get(team2) - goalsAgainst.get(team2)
                                            - (goalsFor.get(team) - goalsAgainst.get(team));
                                } else {
                                    return (goalsFor.get(team2) - goalsAgainst.get(team2))
                                            - (goalsFor.get(team) - goalsAgainst.get(team));
                                }
                            } else {
                                teamsToCompare.add(team);
                            }
                        }
                    }
                    // If two teams have the same number of points and there was no direct match
                    // between them,
                    // or if three or more teams have the same number of points, the same number of
                    // wins in their
                    // direct matches, and the same number of goals scored and conceded, they share
                    // the same position
                    return 0;
                }
            }
        });

        // The winner is the first team in the list
        return teams.get(0);
    }

    public static void testingEliminationTournament() throws IOException {
        Tournament1 tournament = readTour("69");
        ArrayList<String> players = new ArrayList<>(Arrays.asList(tournament.getMembers()));

        Collections.shuffle(players);

        // Conduct elimination matches
        while (players.size() > 1) {
            // Display current round's players
            System.out.println("Current round: " + players);

            // Conduct matches and remove losers
            for (int i = 0; i < players.size() - 1; i += 2) {
                String winner = conductEliminationMatch(players.get(i), players.get(i + 1));
                players.remove(i + 1);
                players.set(i, winner);
            }
        }

        // Display winner
        System.out.println("Winner: " + players.get(0));
    }

    public static String conductEliminationMatch(String player1, String player2) {
        // Replace with your own implementation of match logic
        return Math.random() > 0.5 ? player1 : player2;
    }

    public Tournament1(String ID, String name, String regStart, String regEnd, String startDate, String endDate,
            String tourType, String sport, String[] members) {
        this.ID = ID;
        this.name = name;
        this.regStart = regStart;
        this.regEnd = regEnd;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourType = tourType;
        this.sport = sport;
        this.members = members;
    }

    public static Tournament1 readTour(String ID) throws IOException {
        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(Paths.get(ID + ".txt"));
        int numberOfMembers = 0;

        for (int i = 8; !(list.get(i).equals("@")); i++) {
            numberOfMembers = numberOfMembers + 1;
        }

        String[] members = new String[numberOfMembers];
        int membersListIndex = 0;

        for (int i = 8; !(list.get(i).equals("@")); i++) {
            members[membersListIndex] = list.get(i);
            membersListIndex = membersListIndex + 1;
        }

        return new Tournament1(ID, list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7),
                members);
    }

    public void saveTour() throws IOException {
        FileWriter file = new FileWriter(new File(this.ID + ".txt."));

        String membersData = "";
        for (int i = 0; i < this.members.length; i++) {
            membersData = membersData + members[i] + "\n";
        }

        String data = this.ID + "\n" + this.name + "\n" + this.regStart + "\n" + this.regEnd + "\n" + this.startDate
                + "\n" + this.endDate + "\n" + this.tourType + "\n" + this.sport + "\n" + membersData + "@";
        file.write(data);
        file.close();
    }

    public void addParticipant(String member) {
        ArrayList<String> newMembers = new ArrayList<String>(Arrays.asList(this.members));
        newMembers.add(member);

        this.members = newMembers.toArray(this.members);
    }

    public void removeParticipant(String member) {
        ArrayList<String> newMembers = new ArrayList<String>(Arrays.asList(this.members));
        newMembers.remove(member);

        List<String> resizedNewMembers = newMembers.subList(0, newMembers.size());

        this.members = resizedNewMembers.toArray(new String[0]);
    }

    public boolean getRanking() {
        return true;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegStart() {
        return this.regStart;
    }

    public void setRegStart(String regStart) {
        this.regStart = regStart;
    }

    public String getRegEnd() {
        return this.regEnd;
    }

    public void setRegEnd(String regEnd) {
        this.regEnd = regEnd;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTourType() {
        return this.tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getSport() {
        return this.sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String[] getMembers() {
        ArrayList<String> array = new ArrayList<>();
        File f = new File("registration.txt");
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {

                String line = input.nextLine();
                String[] lineArray = line.split(",", 9);
                if (this.ID.equals(lineArray[1])) {
                    array.add(lineArray[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String dsf[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            dsf[i] = array.get(i);
        }

        return dsf;

    }
public class DatesBetween {
        public static void main(String[] args) {
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 1, 15);
            int stepSize = 5;
    
            List<LocalDate> dates = getDatesBetween(startDate, endDate, stepSize);
            for (LocalDate date : dates) {
                System.out.println(date);
            }
        }
    
        public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate, int stepSize) {
            List<LocalDate> dates = new ArrayList<>();
    
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                dates.add(currentDate);
                currentDate = currentDate.plusDays(stepSize);
            }
    
            return dates;
        }
    }
    public static List<LocalDate> getDatesBetween(String sDate, String eDate, int stepSize) {
            List<LocalDate> dates = new ArrayList<>();
            LocalDate startDate = LocalDate.parse(sDate);
            LocalDate endDate = LocalDate.parse(eDate);
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                dates.add(currentDate);
                currentDate = currentDate.plusDays(stepSize);
            }
    
            return dates;
        }
    

}