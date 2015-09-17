package models;

import com.avaje.ebean.Model;
//import com.avaje.ebean.annotation.DbJson;
import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;


@Entity
@Table(name = "jsonperson")
public class JSONPerson extends Model{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    @DbJsonB
    public JsonNode data;

    public static Finder<Long,JSONPerson> find = new Finder("json",
            Long.class, JSONPerson.class
    );
}
