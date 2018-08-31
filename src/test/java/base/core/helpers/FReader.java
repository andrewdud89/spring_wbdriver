package base.core.helpers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhitnikov on 7/6/2017.
 */
public class FReader {


    /**
     * Json file to JSONObject
     *
     * @param filePath absolute path to file
     * @return JSONObject
     */
    public static JSONObject readJSON(String filePath) {

        return readJSON(new File((filePath)));
    }

    /**
     * Json file to JSONObject
     *
     * @param file File object
     * @return JSONObject
     */
    public static JSONObject readJSON(File file) {
        try {
            JSONTokener obj = new JSONTokener(new FileReader(file));
            return new JSONObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Read json array file
     *
     * @param path abs file path
     * @return JSONArray
     */
    public static JSONArray readJSONArray(String path) {
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(path));
            return new JSONArray(tokener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to read");

        }
    }

    /**
     * File to String
     *
     * @param filename abs file path
     * @return String
     */
    public static String readFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String[]> readCSV(File file, String split) {

        BufferedReader br = null;
        String line = "";
        List<String[]> result = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] row = line.split(split);

                result.add(row);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
