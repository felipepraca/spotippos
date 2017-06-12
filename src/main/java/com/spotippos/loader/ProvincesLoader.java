package com.spotippos.loader;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spotippos.model.Province;
import com.spotippos.model.Provinces;

@Configuration
public class ProvincesLoader {

    private String fileName;

    public ProvincesLoader(@Value("${files.provinces}") String fileName) {
        this.fileName = fileName;
    }

    @Bean
    public List<Province> loadProvinces() {
        Provinces provinces = JsonLoader.loadFile(fileName, Provinces.class);

        provinces.getGode().setName(Provinces.GODE);
        provinces.getGroola().setName(Provinces.GROOLA);
        provinces.getJaby().setName(Provinces.JABY);
        provinces.getNova().setName(Provinces.NOVA);
        provinces.getRuja().setName(Provinces.RUJA);
        provinces.getScavy().setName(Provinces.SCAVY);

        return Arrays.asList(provinces.getGode(),
                            provinces.getGroola(),
                            provinces.getJaby(),
                            provinces.getNova(),
                            provinces.getRuja(),
                            provinces.getScavy());

    }
}
