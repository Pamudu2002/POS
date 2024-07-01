package org.example.pos;
import java.io.*;
import java.util.ArrayList;

public class UserServices implements Serializable {
    private static final long serialVersionUID = 1L;

    public static ArrayList<User> users = new ArrayList<User>();

    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        UserServices.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void saveUsers(ArrayList<User> list) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("database.ser"))) {
            objectOutputStream.writeObject(list);
        } catch (IOException e) {

        }
    }

    public static void updateFile() {
        saveUsers(users);
    }

    public static void retrieveUsers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("database.ser"))){
            users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {

        }
    }


}
