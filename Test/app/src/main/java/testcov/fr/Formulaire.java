package testcov.fr;

import android.util.Log;

public class Formulaire {

    private int age;
    private int masque;
    private int environment;
    private int contact;
    private boolean test;
    private boolean isolation;
    private int R0 = 4;
    private int nbJour;
    private int nbPersonnes = 500;

    public Formulaire(String age, String masque, String place, String contact, String depistage, String isoler, String jour)
    {
        switch(age)
        {
            case "Moins de 15 ans" :
                this.age = -15;
                break;
            case "15-44 ans" :
                this.age = 15;
                break;
            case "45-64 ans" :
                this.age = 45;
                break;
            case "65-74 ans" :
                this.age = 65;
                break;
            case "75 ans et plus" :
                this.age = 75;
                break;
            default :
                Log.i( "DEBUG", "Pour age : valeur inconue" );
        }

        switch(masque)
        {
            case "Personne ne porte de masque" :
                this.masque = 0;
                this.R0 += 1;
                break;
            case "Seul le porteur du virus porte un masque" :
                this.masque = 1;
                break;
            case "Seule la personne non infectée porte un masque" :
                this.masque = 2;
                break;
            case "Tout le monde porte un masque" :
                this.masque = 3;
                this.R0 -= 1;
                break;
            default :
                Log.i( "DEBUG", "Pour masque : valeur inconue" );
        }

        switch(place)
        {
            case "En extérieur" :
                this.environment = 1;
                this.R0 -= 1;
                break;
            case "En intérieur mais bien ventillé" :
                this.environment = 2;
                break;
            case "En intérieur mal aéré" :
                this.environment = 3;
                this.R0 += 1;
                break;
            default :
                Log.i( "DEBUG", "Pour place : valeur inconue" );
        }

        switch(contact)
        {
            // Est-ce que ça ne permettrait pas de définir le nombre de déplacements par j ?
            // Pour l'instant je suis parti comme ça
            case "Moins de 30 minutes" :
                this.contact = 10;
                this.R0 -= 1;
                break;
            case "1 heure" :
                this.contact = 10;
                break;
            case "2-3 heures" :
                this.contact = 5;
                break;
            case "Plus de 3 heures" :
                this.contact = 3;
                break;
            default :
                Log.i( "DEBUG", "Pour contact : valeur inconue" );
        }

        switch(depistage)
        {
            case "Oui" :
                this.test = true;
                break;
            case "Non" :
                this.test = false;
                break;
            default :
                Log.i( "DEBUG", "Pour depistage : valeur inconue" );
        }

        switch(isoler)
        {
            case "Oui" :
                this.isolation = true;
                break;
            case "Non" :
                this.isolation = false;
                break;
            default :
                Log.i( "DEBUG", "Pour isoler : valeur inconue" );
        }

        switch(jour)
        {
            case "15 jour" :
                this.nbJour = 15;
                break;
            case "1 mois" :
                this.nbJour = 31;
                break;
            case "2 mois" :
                this.nbJour = 62;
                break;
            default :
                Log.i( "DEBUG", "Pour place : valeur inconue" );
        }
        Log.i( "DEBUG", "Pour age : " + this.age);
        Log.i( "DEBUG", "Pour port du masque : " + this.masque);
        Log.i( "DEBUG", "Pour lieux de rencontre : " + this.environment);
        Log.i( "DEBUG", "Pour temps de contact : " + this. contact);
        Log.i( "DEBUG", "Pour réalisation des tests : " + this.test);
        Log.i( "DEBUG", "Pour confinement des personnes malades : " + this.isolation);
        Log.i( "DEBUG", "Pour R0 : " + this.R0);
        Log.i( "DEBUG", "Pour nbJour : " + this.nbJour);
        Log.i( "DEBUG", "Pour nbPersonnes : " + this.nbPersonnes);
    }

    public int getAge()
    {
        return this.age;
    }

    public int getMasque()
    {
        return this.masque;
    }

    public int getEnvironment()
    {
        return this.environment;
    }

    public int getContact()
    {
        return this.contact;
    }

    public boolean getTest()
    {
        return this.test;
    }

    public boolean getIsolation()
    {
        return this.isolation;
    }

    public int getR0() { return this.R0; }

    public int getNbJour()
    {
        return this.nbJour;
    }

    public int getNbPersonne()
    {
        return this.nbPersonnes;
    }
}
