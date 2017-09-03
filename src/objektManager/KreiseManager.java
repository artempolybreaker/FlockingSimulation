/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package objektManager;

import aktoeren.Kreis;

import java.util.HashMap;


public class KreiseManager {

    private HashMap<Integer, Kreis> kreise = new HashMap();
    private static KreiseManager exemplar = new KreiseManager();

    private KreiseManager() {
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clonen ist nicht erlaubt");
    }

    public static KreiseManager getExemplar() {
        return exemplar;
    }

    public void registriereKreis(Kreis kreis) {
        this.kreise.put(kreis.getId(), kreis);
    }

    public void entferneKreis(int id) {
        this.kreise.remove(id);
    }

    public Kreis getKreis(int id) {
        return this.kreise.get(id);
    }

    public HashMap<Integer, Kreis> getKreise() {
        return this.kreise;
    }

    public int getAnzahlAllerKreise() {
        return this.kreise.size();
    }

    public void entferneAlleKreise() {
        this.kreise.clear();
    }
}
