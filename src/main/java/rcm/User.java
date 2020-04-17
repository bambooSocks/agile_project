package rcm;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LogisticsCompanyID",
                       discriminatorType = DiscriminatorType.STRING,
                       length = 20)
@DiscriminatorValue("U")

public abstract class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    protected int id;
    @Column
    protected String password;
    @Column
    protected String name;
    @Column
    protected String address;
    @Column
    protected String refPerson;
    @Column
    protected String email;

    public User(String name, String address, String refPerson, String email, String password) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
        this.password = Password.SHA1_Hasher(password);
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRefPerson() {
        return refPerson;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
