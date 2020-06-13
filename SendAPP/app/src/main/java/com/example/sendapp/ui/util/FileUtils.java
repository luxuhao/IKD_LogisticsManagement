
package com.example.sendapp.ui.util;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;


import com.example.sendapp.ui.entity.BarEntry;
import com.example.sendapp.ui.entity.Entry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilities class for interacting with the assets and the devices storage to
 * load and save DataSet objects from and to .txt files.
 * 
 * @author Philipp Jahoda
 */
public class FileUtils {

    private static final String LOG = "MPChart-FileUtils";

    /**
     * Loads a an Array of Entries from a textfile from the sd-card.
     * 
     * @param path the name of the file on the sd-card (+ path if needed)
     * @return
     */
    public static List<Entry> loadEntriesFromFile(String path) {

        File sdcard = Environment.getExternalStorageDirectory();

        // Get the text file
        File file = new File(sdcard, path);

        List<Entry> entries = new ArrayList<Entry>();

        try {
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split("#");

                if (split.length <= 2) {
                    entries.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {

                    float[] vals = new float[split.length - 1];

                    for (int i = 0; i < vals.length; i++) {
                        vals[i] = Float.parseFloat(split[i]);
                    }

                    entries.add(new BarEntry(vals, Integer.parseInt(split[split.length - 1])));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }

        return entries;
    }

    /**
     * Loads an array of Entries from a textfile from the assets folder.
     * 
     * @param am
     * @param path the name of the file in the assets folder (+ path if needed)
     * @return
     */
    public static List<Entry> loadEntriesFromAssets(AssetManager am, String path) {

        List<Entry> entries = new ArrayList<Entry>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(am.open(path), "UTF-8"));

            String line = reader.readLine();

            while (line != null) {
                // process line
                String[] split = line.split("#");

                if (split.length <= 2) {
                    entries.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {

                    float[] vals = new float[split.length - 1];

                    for (int i = 0; i < vals.length; i++) {
                        vals[i] = Float.parseFloat(split[i]);
                    }

                    entries.add(new BarEntry(vals, Integer.parseInt(split[split.length - 1])));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG, e.toString());
                }
            }
        }

        return entries;

        // String label = null;
        // List<Entry> entries = new ArrayList<Entry>();
        //
        // BufferedReader reader = null;
        // try {
        // reader = new BufferedReader(
        // new InputStreamReader(am.open(path), "UTF-8"));
        //
        // // do reading, usually loop until end of file reading
        // label = reader.readLine();
        // String line = reader.readLine();
        //
        // while (line != null) {
        // // process line
        // String[] split = line.split("#");
        // entries.add(new Entry(Float.parseFloat(split[0]),
        // Integer.parseInt(split[1])));
        // line = reader.readLine();
        // }
        // } catch (IOException e) {
        // Log.e(LOG, e.toString());
        //
        // } finally {
        //
        // if (reader != null) {
        // try {
        // reader.close();
        // } catch (IOException e) {
        // Log.e(LOG, e.toString());
        // }
        // }
        // }
        //
        // DataSet ds = new DataSet(entries, label);
        // return ds;
    }

    /**
     * Saves an Array of Entries to the specified location on the sdcard
     * 
     * @param ds
     * @param path
     */
    public static void saveToSdCard(List<Entry> entries, String path) {

        File sdcard = Environment.getExternalStorageDirectory();

        File saved = new File(sdcard, path);
        if (!saved.exists())
        {
            try
            {
                saved.createNewFile();
            } catch (IOException e)
            {
                Log.e(LOG, e.toString());
            }
        }
        try
        {
            // BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(saved, true));

            for (Entry e : entries) {

                buf.append(e.getVal() + "#" + e.getXIndex());
                buf.newLine();
            }

            buf.close();
        } catch (IOException e)
        {
            Log.e(LOG, e.toString());
        }
    }

    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager am, String path) {

        List<BarEntry> entries = new ArrayList<BarEntry>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(am.open(path), "UTF-8"));

            String line = reader.readLine();

            while (line != null) {
                // process line
                String[] split = line.split("#");

                entries.add(new BarEntry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));

                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG, e.toString());
                }
            }
        }

        return entries;
    }

    /**
     * 重命名
     *
     * @param oldName
     * @param newName
     * @return
     */
    public static boolean reNamePath(String oldName, String newName) {
        File f = new File(oldName);
        return f.renameTo(new File(newName));
    }

    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile()) {
                try {
                    Log.i("DirectoryManager deleteFile", fileName);
                    newPath.delete();
                    status = true;
                } catch (SecurityException se) {
                    se.printStackTrace();
                    status = false;
                }
            } else
                status = false;
        } else
            status = false;
        return status;
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        long size = 0;

        File file = new File(filePath);
        if (file != null && file.exists()) {
            size = file.length();
        }
        return size;
    }
}
