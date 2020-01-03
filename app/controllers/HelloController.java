package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Singleton
public class HelloController extends Controller {
    HashMap<Integer,String> usermap = new HashMap<Integer, String>();

    public Result doSomethingFancy(){
        return ok("Hello From Fancy");
    }
    public Result HelloUser(String name){
        String msg="Hello "+name+" !!";
        return ok(msg);
    }
    public Result HelloUserWithDetails(){
        JsonNode requestJson = request().body().asJson();
        String firstName = null;
        String lastName = null;
        if(requestJson.has("first_name")){
            firstName = requestJson.get("first_name").asText();
        }
        if(requestJson.has("last_name")){
            lastName = requestJson.get("last_name").asText();
        }
        if(firstName != null && lastName != null){
            String msg = "Hello " + firstName + " " + lastName;
            return ok(msg);
        }
        return badRequest("Provide proper field details");

    }
    public Result HelloUserWithMap(){
        JsonNode requestJson = request().body().asJson();
        String Name = null;
        int id = 0;
        if(requestJson.has("name")) {
            Name = requestJson.get("name").asText();
        }
        if(requestJson.has("user_id")){
            id = requestJson.get("user_id").asInt();
        }
        if(Name != null && id !=0){
            String msg = "Hello " + Name;
            usermap.put(id,Name);
            System.out.println(usermap);
            return created("User Created!! "+msg);
        }
        return badRequest("Provide proper field details");
    }
    public Result HelloUserID(int id){
        String name = usermap.get(id);
        String msg = "Hello "+name+" !!"+"\nYour id is "+id;
        return ok(msg);
    }
}
