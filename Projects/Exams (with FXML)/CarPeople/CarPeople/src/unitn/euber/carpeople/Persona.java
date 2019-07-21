package unitn.euber.carpeople;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The person class
 * @author Eugenio Vinicio Berretta
 */
public class Persona extends Record {

    /**  List of all the instanciated people */
    public static final List<Persona> PERSONE = new ArrayList<>();
    // Set of all the instanciated people
    private static final Set<Persona> PERSONE_SET = new HashSet<>();
    
    // True if the last instances was added to the set
    private static boolean LAST_ADDED;

    /**
     * Getter of the static field LAST_ADDED
     * @return true if the last instanciated person was added to the set, false otherwise
     */
    public static final boolean getLastAdded() {
        return Persona.LAST_ADDED;
    }

    /**
     * Constructor of the class Persona
     * @param nome the name of the person
     * @param cognome the surname of the person
     * @param anno the year of the person
     */
    public Persona(String nome, String cognome, Integer anno) {
        // Assigns the fields (super constructor)
        super(nome, cognome, anno);
        // Adds the people to the set if it does not exists yet
        Persona.LAST_ADDED = PERSONE_SET.add(this);
        if (Persona.getLastAdded()) {
            // Adds it also to the list, in case the set add worked
            PERSONE.add(this);
        }
    }

    /**
     * Overriding of the equals method
     * @param o the person to compare
     * @return true if the people are equal (same class and fields)
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof Persona;
    }

}
