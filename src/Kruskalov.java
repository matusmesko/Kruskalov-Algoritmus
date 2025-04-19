import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Kruskalov {

    public Kruskalov(String subor) {
        int pocetVrcholov = 0;
        ArrayList<Hrana> hrany = new ArrayList<>();
        String path = "src/data/" + subor;

        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextInt()) {

                int u = scan.nextInt();
                int v = scan.nextInt();
                int cena = scan.nextInt();

                hrany.add(new Hrana(new Vrchol(u,u), new Vrchol(v,v), cena));
                if (pocetVrcholov < v) {
                    pocetVrcholov = v;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Súbor " + path + " Sa nenašiel");
        }

        Graf kruskal = new Graf();
        ArrayList<Hrana> kostra = (ArrayList<Hrana>) kruskal.najdrahsiaKostra(pocetVrcholov, hrany);


        int sumCena = 0;
        for (Hrana h : kostra) {
            sumCena += h.getCena();
        }

        String formattedOutput = formatujKostru(kostra, sumCena);
        System.out.println(formattedOutput);
    }

    public Kruskalov(String subor, int vrchol) {
        ArrayList<Hrana> hrany = new ArrayList<>();
        String path = "src/data/" + subor;

        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextInt()) {

                int u = scan.nextInt();
                int v = scan.nextInt();
                int cena = scan.nextInt();

                hrany.add(new Hrana(new Vrchol(u,u), new Vrchol(v,v), cena));
            }
        } catch (Exception e) {
            throw new RuntimeException("Súbor " + path + " Sa nenašiel");
        }

        Graf kruskal = new Graf();
        ArrayList<Hrana> kostra = (ArrayList<Hrana>) kruskal.najdrahsiaKostra(vrchol, hrany);


        int sumCena = 0;
        for (Hrana h : kostra) {
            sumCena += h.getCena();
        }

        String formattedOutput = formatujKostru(kostra, sumCena);
        System.out.println(formattedOutput);
    }


    private static String formatujKostru(ArrayList<Hrana> kostra, int totalCena) {
        StringJoiner sj = new StringJoiner(", ");
        for (Hrana h : kostra) {
            sj.add(String.format("(%d, %d, cena: %d)",
                    h.getU().getCisloVrchola(),
                    h.getV().getCisloVrchola(),
                    h.getCena()));
        }
        return String.format("Najdrahsia Kostra: [%s]\ncelková cena: %d", sj.toString(), totalCena);
    }
}
