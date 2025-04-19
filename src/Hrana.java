
public class Hrana implements Comparable<Hrana> {
    private int cena;
    private Vrchol u;
    private Vrchol v;

    public Hrana(Vrchol u, Vrchol v, int cena) {
        this.u = u;
        this.v = v;
        this.cena = cena;
    }

    public Vrchol getU() {
        return u;
    }

    public Vrchol getV() {
        return v;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public int compareTo(Hrana other) {
        if (this.cena == other.cena) {
            return 0;
        } else if (this.cena < other.cena) {
            return 1;
        } else {
            return -1;
        }
    }
}
