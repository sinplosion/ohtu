package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private final String[] SCORES = {"Love","Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2)
        {
            return evenScore();
        }
        else if (m_score1>=4 || m_score2>=4)
        {
            return matchPoint();
        }
        else
        {
            return onGoingMatch();
            
        }

    }

    public String evenScore(){
        if(m_score1 >= 4) {
            return "Deuce";
        }
        return SCORES[m_score1] + "-All";
    }

    public String matchPoint(){
        int minusResult = m_score1-m_score2;
        if (minusResult==1) return "Advantage player1";
        else if (minusResult ==-1) return "Advantage player2";
        else if (minusResult>=2) return  "Win for player1";
        else return "Win for player2";
    }

    public String onGoingMatch(){
        String score = "";
        int tempScore = 0;
        for (int i=1; i<3; i++)
        {
            if (i==1) tempScore = m_score1;
            else { score+="-"; tempScore = m_score2;}
            score += SCORES[tempScore];
            }
            return score;
    }
}