package princetonPlainsboro;

class Acte {

    public enum Type {
        diagnostique, therapeutique;
    }

    private Code code;
    private String nom;
    private Patient pat;
    private Medecin med;
    private Date datereal;
    private Type type;
    private int coef;
    private String observations;

    public Acte(Code code, String nom, Patient pat, Medecin med, Date datereal, Type type, int coef) {
        this.code = code;
        this.nom = nom;
        this.pat = pat;
        this.med = med;
        this.datereal = datereal;
        this.type = type;
        this.coef = coef;
        this.observations = "";
    }

    public void ajoutObservations(String o) {
        this.observations = o;
    }

    public String toString() {
        String s = "Code de l'acte : ";
        s += code.toString()
                + ", nom de l'acte : " + nom
                + ", nom du patient : " + pat.getNom()
                + ", nom du médecin : " + med.getNom()
                + ", date de réalisation : " + datereal.toString()
                + ", Type : " + type
                + ", coefficient : " + coef;
        if (!observations.isEmpty()) {
            s += ", observations : " + observations;
        }

        return s;
    }

    public Patient getPat() {
        return pat;
    }

    public double cout() {
        return code.calculerCout(coef);
    }
}

