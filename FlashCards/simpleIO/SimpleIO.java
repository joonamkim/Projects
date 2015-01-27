package simpleIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * Provides a simple package for reading and writing files.
 * 
 * @author Dave Matuszek
 * @version Mar 27, 2009
 */
public class SimpleIO {
    static String fileName;
    
    /**
     * Reads in lines from a user-chosen file.
     * 
     * @return The list of lines.
     * @throws IOException In the event of an error.
     */
    public static ArrayList<String> load() throws IOException {
        ArrayList<String> lines = null;
        BufferedReader reader;

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load which file?");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                fileName = file.getCanonicalPath();
                reader =
                    new BufferedReader(new FileReader(fileName));
                lines = new ArrayList<String>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
                return lines;
            }
        }
        return lines;
    }
    
    /**
     * Does the actual work of saving lines to a file.
     * @param lines The lines to be saved.
     * @throws IOException In the event of an error.
     */
    public static void save(ArrayList<String> lines) throws IOException {
        if (fileName == null) {
            saveAs(lines);
        } else {
            writeTo(fileName, lines);
        }
    }
    
    /**
     * Saves lines to a user-specified file.
     * 
     * @param lines The lines to be saved.
     * @throws IOException In the event of an error.
     */
    public static void saveAs(ArrayList<String> lines) throws IOException {
        JFileChooser chooser = new JFileChooser();
        
        chooser.setDialogTitle("Save file as? ");
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                fileName = file.getCanonicalPath();
                writeTo(fileName, lines);
            }
        }
    }

    /**
     * Saves lines to the same file from which they were
     * previously read.
     * 
     * @param outputFileName The name of the file.
     * @param lines The lines to be saved.
     * @throws IOException If no file was previously read, or
     *      the file could not be written.
     */
    private static void writeTo(String outputFileName, ArrayList<String> lines)
            throws IOException {
        PrintWriter writer;

        if (outputFileName == null) {
            throw new IOException("No file has been loaded.");
        }
        writer = new PrintWriter(new FileOutputStream(outputFileName));
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }
    
    /**
     * FOR TESTING PURPOSES ONLY.
     * @param args Unused.
     * @throws IOException In the event of an error.
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> lines;
        lines = load();
        saveAs(lines);
    }
}
