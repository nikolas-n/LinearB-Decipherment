package io.kpf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Katie on 24/12/2016.
 */
public class Entry implements Serializable{
    // Khania, Knossos, Midea, Mycenae, Pylos, Thebes, Tiryns

    public String word;
    public String definition;
    public boolean Khania;
    public boolean Knossos;
    public boolean Midea;
    public boolean Mycenae;
    public boolean Pylos;
    public boolean Thebes;
    public boolean Tiryns;

    public Entry(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }
    
    public ArrayList<String> getLocations()
    {
        ArrayList<String> returnLocs = new ArrayList<String>();
        if (Khania)
        {
            returnLocs.add("Khania");
        }
        if (Knossos)
        {
            returnLocs.add("Knossos");
        }
        if (Midea)
        {
            returnLocs.add("Midea");
        }
        if (Mycenae)
        {
            returnLocs.add("Mycenae");
        }
        if (Pylos)
        {
            returnLocs.add("Pylos");
        }
        if (Thebes)
        {
            returnLocs.add("Thebes");
        }
        if (Tiryns)
        {
            returnLocs.add("Tiryns");
        }
        
        return returnLocs;
    }
    
    public void print()
    {
        System.out.println(word);
        System.out.println("Definition: " + definition);
        System.out.print(" - ");
        for (String loc: getLocations()) {
            System.out.print(loc + " ");
        }
        System.out.println();
    }
    
    public List<String> makeList(int id)
    {
        //            CSVUtils.writeLine(writer, Arrays.asList("id","word", "definition", "khania", "knossos", "midea","mycenae","pylos","thebes","tiryns"));
        return Arrays.asList("" + id, word, definition, toNumeralString(Khania), toNumeralString(Knossos),toNumeralString(Midea), toNumeralString(Pylos), toNumeralString(Thebes), toNumeralString(Tiryns));
    }

    public String toNumeralString(final Boolean input) {
        if (input == null) {
            return "0";
        } else {
            return input.booleanValue() ? "1" : "0";
        }
    }
}
