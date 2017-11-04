package com.company.helpers;

public class AuctionsCounter {
    private static final String AUCTIONS_COUNTER_FILE = "acntr.txt";
    FileOperation fileOperation = new FileOperation();


    public Integer readCurrentID() {
        if (fileOperation.FileExists(AUCTIONS_COUNTER_FILE) ) {
            return fileOperation.readNumberFromFile(AUCTIONS_COUNTER_FILE);
        } else {
            fileOperation.createNewFile(AUCTIONS_COUNTER_FILE);
            writeCurrentID(0);
            return 0;
        }
    }

    public boolean writeCurrentID(int currentID) {
        if ( fileOperation.FileExists(AUCTIONS_COUNTER_FILE) ) {
            String line = Integer.toString(currentID);
            fileOperation.writeLineToFile(AUCTIONS_COUNTER_FILE,Integer.toString(currentID));
            return true;
        } else return false;
    }
}


/*
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
        if (userToArray != null) {
            if (userToArray[0].equals(user.getLogin())){
                return true;
            }
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

}
*/
