package io.kpf;

import java.io.Serializable;

/**
 * Created by Katie on 10/01/2017.
 */
public class Tablet implements Serializable {
    String location;
    String series;
    String inscription;
    String identifier;
    String original;

    public Tablet(String inscription, String location, String series, String identifier) {
        this.inscription = inscription;
        this.location = location;
        this.series = series;
        this.identifier = identifier;
    }
}
