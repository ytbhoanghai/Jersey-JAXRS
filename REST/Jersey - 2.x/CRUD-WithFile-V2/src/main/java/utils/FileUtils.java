package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // getAll file
    public static List<File> getAllFile(String baseFolder) {
        List<File> result = new ArrayList<>();
        File[] files = new File(baseFolder).listFiles();
        for (File file: files) {
            if (file.isFile()) {
                result.add(file);
            }
        }
        return result;
    }

    // Read
    public static File getFile(String baseFolder, String fileName) {
        String filePath = baseFolder + "/" + fileName;
        try {
            File file = new File(Paths.get(filePath).toAbsolutePath().toString());
            if (file.exists()) {
                return file;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public static File updateFile(InputStream inputStream, String baseFolder, String fileName) {
        String filePath = baseFolder + "/" + fileName;
        try {
            // delete file
            if (delete(fileName, baseFolder)) {
                // store file
                return storeFile(inputStream, baseFolder, fileName);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    // delete
    public static boolean delete(String baseFolder, String fileName) {
        String filePath = baseFolder + "/" + fileName;
        try {
            File file = getFile(fileName, baseFolder);
            if (file.exists()) {
                return file.delete();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    // create
    public static File storeFile(InputStream inputStream, String baseFolder, String fileName) {
        String filePath = baseFolder + "/" + fileName;

        File uploadedFile = new File(Paths.get(filePath).toAbsolutePath().toString());
        try (OutputStream outputStream = new FileOutputStream(uploadedFile)) {
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return uploadedFile;
    }
}