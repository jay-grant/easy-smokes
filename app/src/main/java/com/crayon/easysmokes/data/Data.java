package com.crayon.easysmokes.data;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {

    /**
     * Returns a List of Strings where each string represents a semi-colon-separated
     * 'block' of raw text within a resource file.
     *
     * @param resource a resource id
     * @return a List of Strings
     */
    public static List<String> readBlocks(int resource, Context context) {
        BufferedInputStream bufferedStream = new BufferedInputStream(context.getResources().openRawResource(resource));
        List<String> blocks = new ArrayList<>();

        try {
            byte[] buffer = new byte[bufferedStream.available()];

            if (bufferedStream.read(buffer) != 0) {
                String contents = new String(buffer);
                blocks = new ArrayList<>();

                Collections.addAll(blocks, contents.split(";"));
            }
            bufferedStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    public static List<String> readRows(int resource, Context context) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new BufferedInputStream(context.getResources().openRawResource(resource))));
        List<String> content = new ArrayList<>();

        try {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            while (line != null) {
                content.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
