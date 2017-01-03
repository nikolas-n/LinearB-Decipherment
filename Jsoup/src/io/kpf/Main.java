package io.kpf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) {
        File folder = new File("C:\\Users\\Katie\\Documents\\linearBCorpus\\Linear B\\minoan.deaditerranean.com\\resources\\linear-b-sign-groups");
        FileManager fileManager = new FileManager();
        //ArrayList<File> fileArrayList = fileManager.listFilesForFolder(folder);
        //fileManager.makeFile(fileArrayList);
        
        ArrayList<Entry> entries = fileManager.getListFromFile();
        
        Analysis analysis = new Analysis();
        // Khania, Knossos, Midea, Mycenae, Pylos, Thebes, Tiryns

        /*analysis.locationSearch(entries, "Khania");
        analysis.locationSearch(entries, "Knossos");
        analysis.locationSearch(entries, "Midea");
        analysis.locationSearch(entries, "Mycenae");
        analysis.locationSearch(entries, "Pylos");
        analysis.locationSearch(entries, "Thebes");
        analysis.locationSearch(entries, "Tiryns");*/
        
        ArrayList<ArrayList<String>> spiltWords = analysis.spiltWords(entries);
            
        //ArrayList<CharacterCount> characterCounts = analysis.findCommonEndings(spiltWords);
        //ArrayList<CharacterCount> characterCounts2 = analysis.findCommonPrefixes(spiltWords);
        ArrayList<CharacterCount> characterCounts3 = analysis.findCommonMiddleWords(spiltWords);

        String csvFile = "C:\\Users\\Katie\\Documents\\Jsoup\\middle.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.<String>asList("middle", "count"));
        for (CharacterCount characterCount : characterCounts3) {
                CSVUtils.writeLine(writer, Arrays.<String>asList(characterCount.value, characterCount.count+""));
        }
        writer.flush();
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
