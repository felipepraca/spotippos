package com.spotippos.loader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

/**
 * Classe que efetua a leitura e parser de arquivos json
 * 
 * @author Felipe
 *
 */
public class JsonLoader {

    private static Gson gson = new Gson();

    /**
     * Lê arquivo e parsea para classe desejada T.
     * 
     * @param fileName (Nome do arquivo com extensão)
     * @param clazz (Tipo de classe a ser parseada)
     * @return
     */
    public static <T> T loadFile(String fileName, Class<T> clazz) {
        InputStream in = JsonLoader.class.getClassLoader().getResourceAsStream(fileName);
        Reader reader = new InputStreamReader(in);
        return gson.fromJson(reader, clazz);
    }
}
