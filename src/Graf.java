import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graf {
    public List<Hrana> najdrahsiaKostra(int pocetVrcholov, List<Hrana> hrany) {
        List<Hrana> kostra = new ArrayList<>();
        Collections.sort(hrany);

        List<Vrchol> vrcholy = new ArrayList<>();
        hrany.forEach(h -> {
            vrcholy.add(h.getU());
            vrcholy.add(h.getV());
        });

        int aktualnaHrana = 0;

        while (kostra.size() != pocetVrcholov - 1) {
            Hrana h = hrany.get(aktualnaHrana);
            Vrchol u = h.getU();
            Vrchol v = h.getV();

            if (u.getKomponent() != v.getKomponent()) {
                int predoslyKomponent = v.getKomponent();
                int novyKomponent = u.getKomponent();

                for (Vrchol vrchol : vrcholy) {
                    if (vrchol.getKomponent() == predoslyKomponent) {
                        vrchol.setKomponent(novyKomponent);
                    }
                }

                kostra.add(h);
            }

            aktualnaHrana++;

            if (aktualnaHrana >= hrany.size() && kostra.size() != pocetVrcholov - 1) {
                throw new IllegalArgumentException("Graf nie je súvislý");
            }
        }

        return kostra;
    }
}