package io.kpf;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Katie on 28/12/2016.
 */
public class Analysis {
    public void locationSearch(ArrayList<Entry> entries, String location)
    {
        int totalWords = 0;
        int uniqueWords = 0;
        int nameCount = 0 ;
        int locCount = 0 ;
        for (Entry entry : entries)
        {
            if(entry.getLocations().contains(location))
            {
                totalWords++;
            }
            if(entry.getLocations().contains(location) && entry.getLocations().size() == 1)
            {
                if(entry.definition.toLowerCase().contains("anthroponym")) {
                    nameCount++;
                }
                if(entry.definition.toLowerCase().contains("toponym")) {
                    locCount++;
                }
                uniqueWords++;
            }
        }
        System.out.println("Data for " + location);
        System.out.println(" Total words: " + totalWords);
        System.out.println(" Unique words: " + uniqueWords);
        System.out.println(" Anthropnyms: " + nameCount);
        System.out.println(" Toponyms: " + locCount);
    }

    public ArrayList<ArrayList<String>> spiltWords(ArrayList<Inflection> inflections){
        ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
        for (Inflection inflection : inflections) {
            for (String word: inflection.words) {
                ArrayList<String> characters = new ArrayList<String>(Arrays.asList(word.split("-")));
                returnList.add(characters);
            }
        }
        return returnList;
    }
    
    public ArrayList<CharacterCount> findCommonEndings(ArrayList<ArrayList<String>> returnList)
    {
        ArrayList<CharacterCount> result = new ArrayList<CharacterCount>();
        result.add(new CharacterCount("test"));
        
        for (ArrayList<String> characters : returnList)
        {
            boolean flag = false;
            String lastChar = characters.get(characters.size()-1);
            CharacterCount characterCount = new CharacterCount(lastChar);

            for (CharacterCount cCount: result) {
                if(characterCount.value.equals(cCount.value))
                {
                    cCount.count++;
                }
                else {
                    flag = true;
                }
            }
            
            if(flag)
            {
                result.add(characterCount);
            }
        }

        return result;
    }
    
    public ArrayList<CharacterCount> findCommonPrefixes(ArrayList<ArrayList<String>> returnList)
    {
        int counta = 0;
        ArrayList<CharacterCount> result = new ArrayList<CharacterCount>();
        result.add(new CharacterCount("test"));

        for (ArrayList<String> characters : returnList)
        {
            boolean flag = false;
            String firstChar = characters.get(0);
            CharacterCount characterCount = new CharacterCount(firstChar);

            System.out.println("Word");
            for (String s: characters
                 ) {
                System.out.print(s + " ");
            }
            System.out.println();

            System.out.println("Starting character: " + firstChar);
            
            if(firstChar.equals("a"))
            {
                counta++;
            }

            for (CharacterCount cCount: result) {
                if(characterCount.value.equals(cCount.value))
                {
                    cCount.count++;
                }
                else {
                    flag = true;
                }
            }

            if(flag)
            {
                result.add(characterCount);
            }
        }

        System.out.println(counta);
        return result;
    }

    public ArrayList<CharacterCount> findCommonMiddleWords(ArrayList<ArrayList<String>> returnList)
    {
        ArrayList<CharacterCount> result = new ArrayList<CharacterCount>();
        result.add(new CharacterCount("test"));

        for (ArrayList<String> characters : returnList)
        {
            for (int i = 1; i < characters.size()-1; i++) {
                boolean flag = false;
                CharacterCount characterCount = new CharacterCount(characters.get(i));

                for (CharacterCount cCount : result) {
                    if (characterCount.value.equals(cCount.value)) {
                        cCount.count++;
                    } else {
                        flag = true;
                    }
                }

                if (flag) {
                    result.add(characterCount);
                }
            }            
        }

        return result;
    }

    public ArrayList<CharacterCount2> doEverything(ArrayList<ArrayList<String>> returnList)
    {
        ArrayList<CharacterCount2> result = new ArrayList<CharacterCount2>();
        result.add(new CharacterCount2("test"));

        for (ArrayList<String> word : returnList)
        {
            for (int i = 0; i < word.size(); i++) {
                CharacterCount2 characterCount = new CharacterCount2(word.get(i));
                boolean alreadyInList = false;

                for (CharacterCount2 cCount : result) {
                    if(characterCount.value.equals(cCount.value))
                    {
                        alreadyInList = true;
                        characterCount = cCount;
                    }
                }
                characterCount.total++;
                
                // word is at the start of the list
                if(i == 0)
                {
                    characterCount.start++;
                } else if(i == word.size()-1)
                {
                    characterCount.end++;
                }
                else {
                    characterCount.middle++;
                }
                

                if (!alreadyInList) {
                    result.add(characterCount);
                }
            }
        }

        return result;
    }
}
