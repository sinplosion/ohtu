package ohtu;

import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {
    private JButton undo;
    private Sovelluslogiikka sovellus;

    private HashMap<JButton, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(JTextField tuloskentta, JTextField syotekentta, JButton plus, JButton miinus,
            JButton nollaa, JButton undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() != undo) {
            Komento komento = komennot.get((JButton) event.getSource());
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
    }
}