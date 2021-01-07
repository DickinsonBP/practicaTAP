package OOP;

public class User {
    private String userName,name;
    private int dateBirth;

    public User(String userName, String name, int dateBirth) {
        this.userName = userName;
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return ""+userName+"-"+name+"-"+dateBirth;
    }
}
