
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }


    public void setGoals(int goals) {
        this.goals = goals;
    }


    public int getGoals() {
        return goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }


    public int getAssists() {
        return assists;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        int sum = goals + assists;
        return name + "\t" + team + "\t" + goals + " + " + assists + " = " + sum;
    }

    @Override
    public int compareTo(Player p) {
        int pSum = p.getAssists() + p.getGoals();
        int tSum = this.getAssists() + this.getGoals();

        return pSum - tSum;
    }
      
}
