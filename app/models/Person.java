package models;


import com.avaje.ebean.Model;

import javax.persistence.*;


@Entity
public class Person extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public String name;

    public static Finder<Long,Person> find = new Finder(
            Long.class, Person.class
    );

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
        return;
    }

}
