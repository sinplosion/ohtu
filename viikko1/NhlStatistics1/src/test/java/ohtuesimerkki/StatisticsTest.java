package ohtuesimerkki;

import org.junit.Before;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void hakuLoytaaPelaajan() {
        assertNotNull(stats.search("Kurri"));
    }

    @Test
    public void hakuEiLoydaPelaajaa() {
        assertNull(stats.search("Garbage"));
    }

    @Test
    public void hakuLoytaaJoukkueen() {
        List<Player> team = stats.team("PIT");
        assertTrue(team.get(0).getName().equals("Lemieux"));
    }

    @Test
    public void topScorers() {
        assertTrue(stats.topScorers(1).get(0).getPoints() == 124);

    }
}