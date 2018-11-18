package org.stas.demo;

/**
 * contains utility stuff
 */
public abstract class Utils {

    private static final String FILE_EXTENSION_TOKEN = ".";

    /**
     * extracts the file extension from a file name
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        String fileExtension = "";
        if (fileName != null && !fileName.isEmpty()) {
            fileExtension = fileName.substring(fileName.lastIndexOf(FILE_EXTENSION_TOKEN));
        }
        return fileExtension;
    }

}
