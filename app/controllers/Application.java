package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.JSONNoID;
import models.JSONPerson;
import models.Person;
import play.data.Form;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    /***************************************
    * Simple CRUD with relational database *
    ***************************************/

    final static Form<Person> personForm = Form.form(Person.class);
    static List <Person> pList = Person.find.all();

    public Result index() {
        pList = Person.find.all();
        return ok(index.render(personForm, pList));
    }

    public Result create() {
        Form<Person> filledForm = personForm.bindFromRequest();
        Person created = filledForm.get();
        created.save();
        pList = Person.find.all();
        return ok(index.render(personForm, pList));
    }

    @Transactional
    public Result update(Long id){
        Person.find.ref(id).setName(personForm.bindFromRequest().get().name);
        Person.find.ref(id).update();
        pList = Person.find.all();
        return ok(index.render(personForm, pList));
    }

    public Result delete(Long id) {
        Person.find.ref(id).delete();
        pList = Person.find.all();
        return ok(index.render(personForm, pList));
    }

    public Result list(){
        pList = Person.find.all();
        return ok(list.render(pList));
    }

    /************************************************
    * CRUD for JSON database with an ID Primary Key *
    ************************************************/

    static List <JSONPerson> jList = JSONPerson.find.on("json").all();

    public Result modelToJSON(){
        pList = Person.find.all();
        for(Person person : pList){
            JsonNode personJson = Json.toJson(person);
            JSONPerson created = new JSONPerson();
            created.data = personJson;
            created.insert("json");
        }

        jList = JSONPerson.find.on("json").all();
        return ok(jsonindex.render(jList));
    }

    public Result jsonindex() {
        jList = JSONPerson.find.on("json").all();
        return ok(jsonindex.render(jList));
    }

    /*********************************************
    * CRUD for JSON database with NO Primary Key *
    *********************************************/

    static List <JSONNoID> jNoIdList = JSONNoID.find.on("json").all();

    public Result jsonNoId(){

        pList = Person.find.all();
        for(Person person : pList){
            JsonNode personJson = Json.toJson(person);
            JSONNoID created = new JSONNoID();
            created.data = personJson;
            created.insert("json");
        }
        jNoIdList = JSONNoID.find.on("json").all();
        return ok(jsonNoIdIndex.render(jNoIdList));
    }

    public Result jsonNoIdIndex(){
        jNoIdList = JSONNoID.find.on("json").all();
        return ok(jsonNoIdIndex.render(jNoIdList));
    }

    public Result jsonNoIdEquality(){
        JsonNode value = Json.parse("{\"a\":1}");
        JSONNoID created = new JSONNoID();
        created.data = value;
        jNoIdList = JSONNoID.find.on("json").where().eq("data", "{\"a\":1}").findList();

        return ok(jsonNoIdIndex.render(jNoIdList));
    }
}
