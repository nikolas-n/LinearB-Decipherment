package io.kpf;

import java.util.ArrayList;

/**
 * Created by Katie on 05/02/2017.
 */
public class Inflection {
    String stem;
    boolean stemConfirmed;
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<Integer> wordCounts = new ArrayList<Integer>();
    int count;

    public Inflection(String stem, boolean stemConfirmed)
    {
        this.stem = stem;
        this.stemConfirmed = stemConfirmed;
        this.count = 0;
    }

}
