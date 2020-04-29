package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki = mock(Pankki.class);
    Viitegeneraattori viitelaskuri = mock(Viitegeneraattori.class);
    Varasto varasto = mock(Varasto.class);
    Kauppa k = new Kauppa(varasto, pankki, viitelaskuri);

    @Before
    public void setUp() {

        when(viitelaskuri.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(11);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 6));

        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "banaani", 99));

        k.aloitaAsiointi();
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostokenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void ostetaanKaksiEriTuotettaTilisiirrollaOikeillaArvoilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(11));
    }

    @Test
    public void ostetaanKaksiEriSamaaTilisiirrollaOikeillaArvoilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void ostetaanKaksiEriTuotettaJoistaToinenLoppuTilisiirrollaOikeillaArvoilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void aloitaAsiointiNollaaKorin() {

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);

        k.aloitaAsiointi();

        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));

    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksulle() {
        when(viitelaskuri.uusi()).thenReturn(43).thenReturn(44).thenReturn(45);

        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(43), eq("12345"), eq("33333-44455"), eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(44), eq("12345"), eq("33333-44455"), eq(6));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(45), eq("12345"), eq("33333-44455"), eq(5));

    }

    @Test
    public void koristaPoistoOnnistuu() {
          
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        
        Tuote tuote = varasto.haeTuote(1);

        verify(varasto, times(1)).palautaVarastoon(tuote);


    }

}