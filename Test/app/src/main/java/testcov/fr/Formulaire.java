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
            case "@string/lblmoins15" :
                this.age = -15;
                break;
            case "@string/lbl15ans" :
                this.age = 15;
                break;
            case "@string/lbl45ans" :
                this.age = 45;
                break;
            case "@string/lbl65ans" :
                this.age = 65;
                break;
            case "@string/lbl75ans" :
                this.age = 75;
                break;
            default :
                Log.i( "DEBUG", "Pour age : valeur inconue" );
        }

        switch(masque)
        {
            case "@string/lblNoOneMasque" :
                this.masque = 0;
                this.R0 += 1;
                break;
            case "@string/lblSeekMasque" :
                this.masque = 1;
                break;
            case "@string/lblNotInfectMasque" :
                this.masque = 2;
                break;
            case "@string/lblEveryMasque" :
                this.masque = 3;
                this.R0 -= 1;
                break;
            default :
                Log.i( "DEBUG", "Pour masque : valeur inconue" );
        }

        switch(place)
        {
            case "@string/lblExterieur" :
                this.environment = 1;
                this.R0 -= 1;
                break;
            case "@string/lblInterieurBon" :
                this.environment = 2;
                break;
            case "@string/lblInterieurMauvais" :
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
            case "@string/lbl30min" :
                this.contact = 10;
                this.R0 -= 1;
                break;
            case "@string/lbl1h" :
                this.contact = 10;
                break;
            case "@string/lbl2h" :
                this.contact = 5;
                break;
            case "@string/lbl3h" :
                this.contact = 3;
                break;
            default :
                Log.i( "DEBUG", "Pour contact : valeur inconue" );
        }

        switch(depistage)
        {
            case "@string/lblOui" :
                this.test = true;
                break;
            case "@string/lblNon" :
                this.test = false;
                break;
            default :
                Log.i( "DEBUG", "Pour depistage : valeur inconue" );
        }

        switch(isoler)
        {
            case "@string/lblOui" :
                this.isolation = true;
                break;
            case "@string/lblNon" :
                this.isolation = false;
                break;
            default :
                Log.i( "DEBUG", "Pour isoler : valeur inconue" );
        }

        switch(jour)
        {
            case "@string/lbl15jour" :
                this.nbJour = 15;
                break;
            case "@string/lbl1month" :
                this.nbJour = 31;
                break;
            case "@string/lbl2month" :
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
