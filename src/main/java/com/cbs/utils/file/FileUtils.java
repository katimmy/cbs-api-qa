package com.cbs.utils.file;


import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cbs.utils.LoggerUtil;
import com.cbs.utils.date.DateUtils;

public class FileUtils {

    // Class Constants
    LoggerUtil log = new LoggerUtil(this.getClass());

    /**
     * Write content to the file
     *
     * @param filePath
     * @param data
     */
    public void writeToFile(String filePath, String data) {
        try {
            File file = createFile(filePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
            log.info("Writing file to :" + filePath);
        } catch (IOException e) {
            log.error("Failed writing data to file :" + e.getLocalizedMessage());
        }
    }

    /**
     * Write content to the file
     *
     * @param filePath
     * @param data
     */
    public void writeToFile(String filePath, ArrayList<String> data) {
        deleteFile(filePath);
        try {
            File file = createFile(filePath);
            FileWriter fileWriter = new FileWriter(file);
            for (String line : data) {
                fileWriter.write(line + System.lineSeparator());
            }
            fileWriter.close();
            log.info("Writing file to :" + filePath);
        } catch (Exception e) {
            log.error("Failed writing data to file :" + e.getLocalizedMessage());
        }
    }

    /**
     * Write content to the existing file
     *
     * @param filePath
     * @param data
     */
    public void appendToFile(String filePath, ArrayList<String> data) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file = createFile(filePath);
            }
            FileWriter fileWriter = new FileWriter(file, true);
            //Append timestamp
            fileWriter.write("Date generated : " + new DateUtils().getDate("MM/dd/yyy HH:MM") + System.lineSeparator());
            for (String line : data) {
                fileWriter.write(line + System.lineSeparator());
            }
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
            log.info("Writing file to: " + filePath);
        } catch (Exception e) {
            log.error("Failed writing data to output file: " + e.getLocalizedMessage());
        }
    }

   

    /**
     * Create file
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return file;
    }

    /**
     * This method will return file by passing filepath
     *
     * @param filePath
     */
    public File getFile(String filePath) {
        return new File(filePath);
    }

    /**
     * Delete file
     *
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath) {
        boolean isFileDeleted = true;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            } else {
                log.warn("File does not exist.");
                isFileDeleted = false;
            }
        } catch (Exception e) {
            log.warn("Error deleting file :" + e.getLocalizedMessage());
        }
        return isFileDeleted;
    }

    /**
     * Erase empty lines from the file
     *
     * @param fileName
     * @param lineLength
     * @throws IOException
     */
    public void eraseEmptyLine(String fileName, int lineLength) throws IOException {
        byte b;
        RandomAccessFile f = new RandomAccessFile(fileName, "rw");
        long length = f.length() - 1;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while (b != 10);
        f.setLength(length + lineLength);
        f.close();
    }

    /**
     * Create File For List
     *
     * @param file,list *
     * @throws Exception
     */
    public void createFileForList(File file, List list) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fo = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));
        log.info("Text File name is :" + file.getName());
        for (int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)));
            bw.newLine();
        }
        bw.close();
        fo.close();
    }

    /**
     * Get data from a file & Store it in a List
     *
     * @param filePath *
     * @throws Exception
     */
    public List getFileDataIntoList(String filePath) throws Exception {
        Scanner s = new Scanner(new File(filePath));
        List<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();
        return list;
    }

    /**
     * copy file from source to destination file
     *
     * @param sourceFile
     * @param destinationFile
     */
    public File copyFile(String sourceFile, String destinationFile) {
        File source = new File(sourceFile);
        File destination = new File(destinationFile);

        //overwrite existing file, if exists
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };

        try {
            Files.copy(source.toPath(), destination.toPath(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    // Used in Network Drive Cleanup utility
    public void convertListToTxt(File file, ArrayList list) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fo = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));
        log.info("-------------- START writing data to Text File >> " + file.getName() + " -------------- ");
        for (int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)));
            bw.newLine();
        }
        log.info("--------------------- END of Data writing >> TEXT FILE IS READY -------------------- ");
        bw.close();
        fo.close();
    }

    /**
     * verifies if File is available in the given filepath
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public Boolean isFileAvailable(String filePath, String fileName) {
        Boolean isFileAvailable = false;
        filePath = filePath + "\\" + fileName;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                isFileAvailable = true;
            }
            log.info("File " + fileName + "is available in the path :" + filePath);
        } catch (Exception e) {
            log.error("File " + fileName + "is not available in the path :" + filePath);
        }
        return isFileAvailable;
    }
}
