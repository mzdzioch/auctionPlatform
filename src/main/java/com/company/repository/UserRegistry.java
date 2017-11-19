package com.company.repository;

import com.company.exceptions.LoginExistException;
import com.company.model.User;

import java.io.*;

public class UserRegistry {

    private String filename;
    UserRegistry userRegistry;

    public UserRegistry(String filename) {
        this.filename = filename;
        File file = new File(filename);
        if (!file.exists()) {
            createFile(filename);
        }
    }


    public void addUser(User user) throws IOException, LoginExistException {

        if (existUser(user))
            throw new LoginExistException("Login exists.");
        else
            addToFile(user);
    }

    public boolean existUser(User user) throws IOException {

        return checkIfUserExist(user);

    }

    private void addToFile(User user) {

        writeUser(user);

    }

    private void createFile(String filename) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null)
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
                if (bw != null)
                    bw.close();

                if (fw != null)
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

                if (userToArray[0].equals(user.getLogin())) {
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
        if (userToArray != null) {
            if (userToArray[0].equals(user.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public boolean isLoginAndPasswordCorrect(User user) throws IOException {


        String[] userToArray = findUser(user);
        if (userToArray[0].equals(user.getLogin()) && userToArray[1].equals(user.getPassword())) {
            return true;
        }

        return false;
    }


}
