package applicationpackage;

import java.io.*;

public class User  {
    private String name;
    private String username;
    private int age;
    private long nidOrBid;
    private String password;
    private String gender;

    public User(String name, String username, int ageInt, String gender, long nidOrBidLong, String password) {
        this.name = name;
        this.username = username;
        this.age = ageInt;
        this.gender = gender;
        this.nidOrBid = nidOrBidLong;
        this.password = password;
    }

    public boolean registerUser() {
        try (FileWriter fileWriter = new FileWriter("database\\UserData.txt", true)) {
            fileWriter.write(name + " $ " + username + " $ " + age + " $ " + gender + " $ " + nidOrBid + " $ " + password + System.lineSeparator());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
