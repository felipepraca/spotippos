package com.spotippos.loader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

public class JsonLoader {

    private static Gson gson = new Gson();

    public static <T> T loadFile(String fileName, Class<T> clazz) {
        InputStream in = JsonLoader.class.getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(in);
        return gson.fromJson(reader, clazz);
    }
}
