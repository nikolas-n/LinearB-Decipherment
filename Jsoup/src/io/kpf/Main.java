package io.kpf;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Tab;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.crypto.AEADBadTagException;

import static java.lang.StrictMath.abs;
import static java.lang.String.join;

public class Main {

    public static void main(String[] args) {

        File newfile = new File("file.txt");
        System.out.println(newfile.getAbsolutePath());

        /*FileManager fileManager = new FileManager();
        ArrayList<Tablet> tablets =  fileManager.getTabletsFromFile();
        int inflectionCase = 0;
        for(Tablet tablet : tablets)
        {
            //System.out.println("case "+ inflectionCase);
            //System.out.println(tablet.inscription);
            //inflectionCase++;
        }**/


        /*try {
            FileInputStream fileIn = new FileInputStream("D:\\Documents\\University\\Year 3\\FYP\\LinearB-Decipherment\\Jsoup\\tablets.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tablets = (ArrayList<Tablet>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        String csvFile = "D:\\Documents\\University\\Year 3\\FYP\\LinearB-Decipherment\\Jsoup\\longTablets.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.<String>asList("identifier", "inscription", "numberOfCharacters"), ';');
            for (Tablet tablet : tablets) {
                String[] count = tablet.inscription.split(" *, *");
                CSVUtils.writeLine(writer, Arrays.<String>asList(tablet.identifier, tablet.inscription
                        , count.length+""), ';');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //String location;
        //String series;
        //String inscription;
        //String identifier;
        //String original;
        //File folder = new File("C:\\Users\\Katie\\Documents\\linearBCorpus\\Linear B\\minoan.deaditerranean.com\\resources\\linear-b-sign-groups");


            //fileManager.makeFile(fileArrayList);
        
        //ArrayList<Entry> entries = fileManager.getListFromFile();
        
        //Analysis analysis = new Analysis();
        // Khania, Knossos, Midea, Mycenae, Pylos, Thebes, Tiryns

        /*analysis.locationSearch(entries, "Khania");
        analysis.locationSearch(entries, "Knossos");
        analysis.locationSearch(entries, "Midea");
        analysis.locationSearch(entries, "Mycenae");
        analysis.locationSearch(entries, "Pylos");
        analysis.locationSearch(entries, "Thebes");
        analysis.locationSearch(entries, "Tiryns");*/
        
        /*ArrayList<ArrayList<String>> spiltWords = analysis.spiltWords(entries);
        for (ArrayList<String> word: spiltWords
             ) {
            for (String s: word
                 ) {
                System.out.print(s + "-");
            }
            System.out.println();
        }*/
            
        //ArrayList<CharacterCount> characterCounts = analysis.findCommonEndings(spiltWords);
        //ArrayList<CharacterCount> characterCounts2 = analysis.findCommonPrefixes(spiltWords);
        /*ArrayList<CharacterCount> characterCounts3 = analysis.findCommonMiddleWords(spiltWords);

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
        
        ArrayList<CharacterCount2> characterCount2s = analysis.doEverything(spiltWords);
        
        ArrayList<CharacterCount3> charPecentage = new ArrayList<CharacterCount3>();

        for (CharacterCount2 char2: characterCount2s
             ) {
            if(char2.total != 0) {
                CharacterCount3 newPecentage = new CharacterCount3(char2.value);
                
                newPecentage.start = (char2.start* 1.0f / char2.total ) * 100.0f;
                newPecentage.end = (char2.end * 1.0f/ char2.total ) * 100.0f;
                newPecentage.middle = (char2.middle* 1.0f / char2.total ) * 100.0f;
                newPecentage.total = 100;
                
                charPecentage.add(newPecentage);
            }
        }
        
        
        String csvFile = "C:\\Users\\Katie\\Documents\\University year 3\\FYP\\LinearB-Decipherment\\Jsoup\\everything-percentage.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.<String>asList("character", "total", "start", "middle", "end"));
            for (CharacterCount3 characterCount : charPecentage) {
                CSVUtils.writeLine(writer, Arrays.<String>asList(characterCount.value, characterCount.total+"", characterCount.start+"", characterCount.middle+"", characterCount.end+""));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        FileManager fileManager = new FileManager();
        ArrayList<Entry> entries = fileManager.getListFromFile();

        ArrayList<Tablet> tablets =  fileManager.getTabletsFromFile();
        ArrayList<Inflection> inflections = new ArrayList<Inflection>();

        String input = "";

        for(Tablet tablet : tablets)
        {

            if(!(tablet.inscription.contains("[")))
            {
                if(!(tablet.inscription.contains("]"))) {
                        input = input + tablet.inscription.replace(", ", "").replace("(", "").replace(")", "");
                }
            }
        }

        // input string, remove punctuation and spilt it
        String[] words = input.split(" ");

        int i = 0;
        for (String word : words)
        {
            int j = 0;
            for (String word2 : words)
            {
                // for each word check against every other word in the input apart from this one
                if(j != i) {
                    // spilt them into arrays of strings
                    String[] charactersWord1 = word.split("-");
                    String[] charactersWord2 = word2.split("-");

                    int likeness = 0;
                    int k = 0;
                    // setup a flag so only the characters in the correct position are chosen
                    boolean flag = true;
                    // check that these aren't the same word
                    if(!word.equals(word2)) {
                        // if they aren't for every character in the first word
                        for (String character : charactersWord1) {
                            // avoid index out of bounds exception by making sure the second word
                            // isn't too short
                            if (charactersWord2.length > k) {
                               // System.out.println(word + " -> " + word2);
                                //System.out.println("k: " + k + " length:" + word2.length() + " " + character + " vs " + charactersWord2[k]);
                                // if the characters are the same, and the flag
                                if (character.equals(charactersWord2[k]) && flag) {
                                    // increase the likeness
                                    likeness++;
                                } else {
                                    // if the characters aren't the same ignore the rest of the word
                                    flag = false;
                                }
                            }
                            k++;
                        }
                    }

                    // if the likeness reaches a threshold we consider it complete
                    if(likeness > 1) {
                        boolean stemFlag = false;
                        String stem = "";
                        // if the likeness - the length of the word is 0 we have the stem of the word
                        if(likeness - charactersWord1.length == 0) {
                            stem = word;
                            stemFlag = true;
                        }
                        // otherwise we need to calculate the stem, this may not be correct
                        else {
                            stemFlag = false;

                            stem = charactersWord1[0] + "";
                            for (int l = 1; l < charactersWord1.length; l++) {
                                if(l < likeness)
                                {
                                    stem = stem + "-" + charactersWord1[l];
                                }
                            }
                        }
                        //System.out.println("Stem: " + stem);
                        // all the related characters we found
                        //System.out.println("    " + word2 + " different characters = " + abs(likeness - word.length()));

                        // ADD ALL CONFIRMED FLAGS
                        boolean stemAlreadyExists = false;
                        for (Inflection inflection: inflections) {
                            // is the word a confirmed stem, and not already in the inflection list, add it
                            if(inflection.stem.equals(stem))
                            {
                                if(stemFlag)
                                {
                                    inflection.stemConfirmed = true;
                                }
                                stemAlreadyExists = true;
                            }
                        }

                        if(!stemAlreadyExists) {
                            inflections.add(new Inflection(stem, stemFlag));
                        }

                        for (Inflection inflection: inflections) {
                            if (inflection.stem.equals(stem)) {
                                if (!inflection.words.contains(word2)) {
                                    inflection.words.add(word2);
                                    inflection.wordCounts.add(1);
                                }
                                else {
                                    inflection.wordCounts.set(inflection.words.indexOf(word2), new Integer(inflection.wordCounts.get(inflection.words.indexOf(word2)).intValue())+1);
                                }
                                inflection.count++;
                            }
                        }

                    }
                }
                j++;
            }
            i++;
        }

        for (Inflection inflection : inflections)
        {
            if(inflection.count > 0) {
                System.out.println(inflection.stem + " Confirmed? " + inflection.stemConfirmed);
                System.out.println("Count: " + inflection.count);
                for (String word : inflection.words) {
                    System.out.println(" - " + word + " " + inflection.wordCounts.get(inflection.words.indexOf(word)));
                }
                System.out.println();
            }
        }
        int total = 0;

        for (Inflection inflection : inflections) {
            total = total+inflection.words.size();
        }

        Analysis analysis = new Analysis();
        ArrayList<ArrayList<String>> arrayLists = analysis.spiltWords(inflections);
        ArrayList<CharacterCount> commonEndings = analysis.findCommonEndings(arrayLists);

        for (CharacterCount c: commonEndings
             ) {
            System.out.println(c.value + ": " + c.count);
        }

        /*
        ArrayList<CharacterCount> commonEndings = analysis.findCommonEndings(arrayLists);
        for (CharacterCount c: commonEndings
             ) {
            System.out.println(c.value + ": " + c.count);
        }
         */

        /*
        String csvFile = "D:\\Documents\\University\\Year 3\\FYP\\LinearB-Decipherment\\Jsoup\\inflection.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.<String>asList("stem", "totalCount", "confirmed", "inflection", "count"));
            for (Inflection inflection : inflections) {
                if(inflection.count > 100) {
                    for (String word : inflection.words
                            ) {
                        CSVUtils.writeLine(writer, Arrays.<String>asList(inflection.stem, inflection.count+"", inflection.stemConfirmed+"",
                                 word, inflection.wordCounts.get(inflection.words.indexOf(word)).toString()+""));
                    }
                }

            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}
