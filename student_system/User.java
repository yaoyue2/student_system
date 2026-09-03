package student_system;


public class User {
    private  String name;
    private  String password;
    private  String personId;
    private  String phoneNumber;

    public User(){

    }
    public User(String name,String pass, String id, String phone){
        this.name=name;
        this.password=pass;
        this.personId=id;
        this.phoneNumber=phone;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
