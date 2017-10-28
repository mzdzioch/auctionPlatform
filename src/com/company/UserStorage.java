package com.company;

import java.io.*;

public class UserStorage {

    public UserStorage(String filename) {
        this.filename = filename;
    }

    private String filename;


    public void createFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;


        try {
            fw = new FileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw !=null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }




    public void writeUser(User newUser) {
        BufferedWriter bw = null;
        FileWriter fw = null;


        try {
            fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);
            bw.write(newUser.getLogin() + " " + newUser.getPassword() + "\n");
            System.out.println("User added to file " + newUser.getLogin() + " " + newUser.getPassword() + "\n");
            //return true;
        } catch (IOException e) {
            e.printStackTrace();
            //return false;
        } finally {
            try {
                if (bw !=null)
                    bw.close();

                if (fw !=null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public boolean checkIfUserExist(User user) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();
            String[] userToArray;

            while (line != null) {
                userToArray = line.split(" ");

                if (userToArray[0].equals(user.getLogin())){
                    return true;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return false;

    }

    /*    UserStorage databaseController = new UserStorage();
    User user = new User("Donald", "Trump1234");
    User user2 = new User("Donald", "Tusk1234");
    User user3 = new User("Misio", "1234");

        databaseController.createFile();

        databaseController.writeUser(user);
        databaseController.writeUser(user2);
        databaseController.writeUser(user3);

        System.out.println(databaseController.checkIfUserExist(user3));*/
}
