package com.spotippos.loader;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.spotippos.model.Province;

public class ProvincesLoaderTest {

    @Test
    public void carregaArquivoDeProvincias() {
        // GIVEN
        ProvincesLoader loader = new ProvincesLoader("provincesTest.json");

        // WHEN
        List<Province> loadProvinces = loader.loadProvinces();

        // THEN
        assertEquals(6, loadProvinces.size());
        assertEquals("Gode", loadProvinces.get(0).getName());
        assertEquals("Groola", loadProvinces.get(1).getName());
        assertEquals("Jaby", loadProvinces.get(2).getName());
        assertEquals("Nova", loadProvinces.get(3).getName());
        assertEquals("Ruja", loadProvinces.get(4).getName());
        assertEquals("Scavy", loadProvinces.get(5).getName());
    }
}
