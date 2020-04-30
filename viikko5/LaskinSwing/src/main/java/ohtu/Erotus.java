package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Erotus extends Komento {
    private int arvo = 0;

    public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo,
            Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);

    }

    @Override
    public void peru() {
        sovellus.plus(arvo);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);

        undo.setEnabled(false);

    }

}