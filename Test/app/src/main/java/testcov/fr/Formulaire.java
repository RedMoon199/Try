package testcov.fr;

import android.util.Log;

public class Formulaire {

    private int age;
    private int masque;
    private int environment;
    private int contact;
    private boolean test;
    private boolean isolation;
    private int nbJour;
    private int nbPersonnes;

    public Formulaire(String age, String masque, String place, String contact, String depistage, String isoler)
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
            case "@string/lblSeekMasque" :
                this.masque = 1;
                break;
            case "@string/lblNotInfectMasque" :
                this.masque = 2;
                break;
            case "@string/lblEveryMasque" :
                this.masque = 0;
                break;
            default :
                Log.i( "DEBUG", "Pour masque : valeur inconue" );
        }

        switch(place)
        {
            case "@string/lblExterieur" :
                this.environment = 1;
                break;
            case "@string/lblInterieurBon" :
                this.environment = 2;
                break;
            case "@string/lblInterieurMauvais" :
                this.environment = 3;
                break;
            default :
                Log.i( "DEBUG", "Pour place : valeur inconue" );
        }

        switch(contact)
        {
            case "@string/lbl30min" :
                this.contact = 1;
                break;
            case "@string/lbl1h" :
                this.contact = 2;
                break;
            case "@string/lbl2h" :
                this.contact = 4;
                break;
            case "@string/lbl3h" :
                this.contact = 6;
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

    public int getNbJour()
    {
        return this.nbJour;
    }

    public int getNbPersonne()
    {
        return this.nbPersonnes;
    }
}
