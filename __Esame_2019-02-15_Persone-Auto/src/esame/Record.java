package esame;

import java.util.Objects;
/**
 *
 * @author Thomas
 */
public abstract class Record {
    //this is stupid AF
    protected String field1;
    protected String field2;
    protected Integer field3;

    public Record(String field1, String field2, Integer field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        if (!Objects.equals(this.field1, other.field1)) {
            return false;
        }
        if (!Objects.equals(this.field2, other.field2)) {
            return false;
        }
        if (!Objects.equals(this.field3, other.field3)) {
            return false;
        }
        return true;
    }
    

    
    //public abstract String easyPrint();
    
    //public abstract String toString();
    
}
