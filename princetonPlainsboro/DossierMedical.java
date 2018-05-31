package princetonPlainsboro;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class DossierMedical {

    private ArrayList<FicheDeSoins> fiches;     // contient des objets de classe 'FicheDeSoins'

    public DossierMedical() {
        fiches = new ArrayList<FicheDeSoins>();  // liste vide
    }

    public ArrayList<FicheDeSoins> getFiches() {
        return fiches;
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
    }

    public void afficher() {
        System.out.println("Dossier medical informatise :");
        System.out.println("-----------------------------");
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.afficher();
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutTotal() {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);

            cout += f.coutTotal();

        }
        return cout;

    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        ArrayList<Patient> liste = new ArrayList<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.toString());
                    liste.add(p);
                }
            }
        }
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public void trierDates() {
        ArrayList<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    public void trierPatient(Patient p) {
        ArrayList<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche les fiches avec le patient
            int i = 0;
            if (p.getNom().equals(copieFiches.get(i).getPatient().getNom())) {
                copieFiches.get(i).afficher();
            }
            i++;

            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(copieFiches.get(i));
        }
    }

    public void trierCout() {
        ArrayList<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.coutTotal() < f1.coutTotal()) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    // tri generique :
    public void trier(ComparaisonFiches c) {
        ArrayList<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    public ArrayList<String> ListePatientDates(Date d1, Date d2) {
        this.trierDates();
        if (d1.compareTo(d2) > 0) {
            Date d3 = d1;
            d1 = d2;
            d2 = d3;
        }

        ArrayList<String> listeP = new ArrayList<String>();
        for (int i = 0; i < this.getFiches().size(); i++) {
            if (this.getFiches().get(i).getDate().compareTo(d1) >= 0 && this.getFiches().get(i).getDate().compareTo(d2) <= 0) {
                listeP.add(this.getFiches().get(i).getPatient().getNom());
            }

        }
        for (int j = 0; j < listeP.size(); j++) {
            for (int k = 1; k < listeP.size() - 1; k++) {
                if (listeP.get(k).equals(listeP.get(j))) {
                    listeP.remove(listeP.get(k));
                }
            }
        }
        return listeP;
    }

}


