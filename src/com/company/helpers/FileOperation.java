package com.company.helpers;

import java.io.*;

public class FileOperation {
    private String filename;

    public boolean FileExists(String filename) {
        File file = new File(filename);
        return file.exists();

    }

    public boolean createNewFile(String filename) {
        if (!FileExists(filename)) {
            createFile(filename);
            return true;
        } else return false;
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

    public Integer readNumberFromFile(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean writeLineToFile(String filename, String lineToWrite) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        createFile(filename);

        try {
            fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);
            bw.write(lineToWrite);
            return true;
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
        return false;

    }



}
