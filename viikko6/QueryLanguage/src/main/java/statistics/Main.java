package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhl27112019.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();

        Matcher m = query.or(query.playsIn("PHI").hasAtLeast(10, "assists").hasFewerThan(8, "goals").build(),

                query.playsIn("EDM").hasAtLeast(20, "points").build()).build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
