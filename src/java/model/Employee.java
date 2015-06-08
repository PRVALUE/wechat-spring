package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Heisaman
 */
public class Employee {
    private String id;
    private String name;
    private String department;

    public Employee(JSONObject obj){
        try{
            this.id = obj.getString("userid");
            this.name = obj.getString("name");
            this.department = obj.getString("department").toString();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
