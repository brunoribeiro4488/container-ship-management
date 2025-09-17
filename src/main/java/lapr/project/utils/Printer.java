package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {

    /**
     * Initializes the Printer
     */
    public Printer(){
    }

    /**
     * prints in a file the information contained in the @param strToPrint
     *
     * @param fileName
     * @param strToPrint
     * @param path
     * @return True if the file is correctly written
     * @throws IOException
     */
    public boolean print(String fileName, String strToPrint, String path) throws IOException {
            String pwd = System.getProperty("user.dir");
            String directory = "\\target\\" + path + "\\";

            File summaries = new File(pwd + "\\target\\" + path);
            if (!summaries.exists()) {
                summaries.mkdirs();
            }

            int i = 0;


            File file;
            do {
                i++;
                file = new File(pwd + directory + fileName + "-Nº" + i + ".txt");
            } while (file.exists());

            FileWriter fileWriter = new FileWriter(file);

        try (PrintWriter asd = new PrintWriter(fileWriter)) {
            asd.printf(strToPrint);
        } catch (Exception e) {
            return false;
        }
            return true;
    }
}
