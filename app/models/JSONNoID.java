package models;

import com.avaje.ebean.Model;
//import com.avaje.ebean.annotation.DbJson;
import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;


@Entity
@Table(name = "jsonnoid")
public class JSONNoID extends Model{

    @DbJsonB
    public JsonNode data;

    public static Finder<Long,JSONNoID> find = new Finder("json",
            Long.class, JSONNoID.class
    );
}
