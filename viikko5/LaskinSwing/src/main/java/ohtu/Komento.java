package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class Komento {
    protected Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    JButton nollaa;
    JButton undo;
    int arvo;

    public Komento(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo,
            Sovelluslogiikka sovellus) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
    }

    public abstract void suorita();

    public abstract void peru();

}
