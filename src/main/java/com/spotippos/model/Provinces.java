package com.spotippos.model;

import com.google.gson.annotations.SerializedName;

public class Provinces {

    public static final String GODE = "Gode";
    public static final String RUJA = "Ruja";
    public static final String JABY = "Jaby";
    public static final String SCAVY = "Scavy";
    public static final String GROOLA = "Groola";
    public static final String NOVA = "Nova";

    @SerializedName(GODE)
    private Province gode;

    @SerializedName(RUJA)
    private Province ruja;

    @SerializedName(JABY)
    private Province jaby;

    @SerializedName(SCAVY)
    private Province scavy;

    @SerializedName(GROOLA)
    private Province groola;

    @SerializedName(NOVA)
    private Province nova;

    public Province getGode() {
        return gode;
    }

    public Province getRuja() {
        return ruja;
    }

    public Province getJaby() {
        return jaby;
    }

    public Province getScavy() {
        return scavy;
    }

    public Province getGroola() {
        return groola;
    }

    public Province getNova() {
        return nova;
    }

}
