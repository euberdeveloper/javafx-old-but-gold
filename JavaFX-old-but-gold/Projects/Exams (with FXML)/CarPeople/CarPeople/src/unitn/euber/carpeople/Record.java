package unitn.euber.carpeople;

import java.util.Comparator;
import java.util.Objects;

/**
 *  The class of a generic record
 * @author Eugenio Vinicio Berretta
 */
public abstract class Record implements Comparable<Record> {
    
    // The comparator of a record
    private static final RecordComparator RECORD_COMPARATOR = new RecordComparator();
    
    /**
     * Getter of the static field RECORD_COMPARATOR
     * @return the value of the static field RECORD_COMPARATOR
     */
    public static final RecordComparator getComparator() {
        return Record.RECORD_COMPARATOR;
    }
    
    // The properties
    protected String field1;
    protected String field2;
    protected Integer field3;
    
    /**
     * Constructor of the class Record
     * @param field1 the first value
     * @param field2 the second value
     * @param field3 the third value
     */
    public Record(String field1, String field2, Integer field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
    
    /**
     * Overriding of the method toString
     * @return the record as a String
     */
    @Override
    public String toString() {
        return this.field1 + " " + this.field2 + " " + this.field3;
    }
    
    /**
     * Overriding of the method equals
     * @param o the object to compare
     * @return true if the object is a record with the same values
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Record) {
            Record r = (Record) o;
            return field1.equals(r.field1) && field2.equals(r.field2) && field3.equals(r.field3);
        }
        return false;
    }

    /**
     * The hashCode of the record
     * @return the hashCode of the record
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.field1);
        hash = 97 * hash + Objects.hashCode(this.field2);
        hash = 97 * hash + this.field3;
        return hash;
    }
    
    /**
     * Overriding of the compareTo method
     * @param r the record to compare
     * @return the result of the comparation of the second field
     */
    @Override
    public int compareTo(Record r) {
        return field2.compareTo(r.field2);
    }

    /**
     * Getter of the first field
     * @return the value of the first field
     */
    public String getField1() {
        return field1;
    }

    /**
     * Setter of the first field
     * @param field1 the new value of the first field
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * Getter of the second field
     * @return the value of the second field
     */
    public String getField2() {
        return field2;
    }
    /**
     * Setter of the second field
     * @param field2 the new value of the second field
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

    /**
     * Getter of the third field
     * @return the value of the third field
     */
    public Integer getField3() {
        return field3;
    }
    /**
     * Setter of the third field
     * @param field3 the new value of the third field
     */
    public void setField3(int field3) {
        this.field3 = field3;
    }
    
    // The comparator for the third field of a record
    private static class RecordComparator implements Comparator<Record> {

        @Override
        public int compare(Record r1, Record r2) {
            return r1.field3 - r2.field3;
        }
        
    }
    
}
