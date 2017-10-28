package com.company;

import java.io.*;

public class UserStorage {
    private String filename;


    public UserStorage(String filename) {
        this.filename = filename;
        File file = new File(filename);
        if (!file.exists()) {
            createFile(filename);
        }
    }




    private void createFile(String filename) {
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
        } catch (IOException e) {
            e.printStackTrace();
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

    private String[] findUser(User user) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();
            String[] userToArray;

            while (line != null) {
                userToArray = line.split(" ");

                if (userToArray[0].equals(user.getLogin())){
                    return userToArray;
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return null;
    }

    public boolean checkIfUserExist(User user) throws IOException {

            String[] userToArray = findUser(user);
            if (userToArray[0].equals(user.getLogin())){
                    return true;
                }

        return false;
    }

    public boolean isLoginAndPasswordCorrect(User user) throws IOException {


        String[] userToArray = findUser(user);
        if (userToArray[0].equals(user.getLogin()) && userToArray[1].equals(user.getPassword())){
            return true;
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
