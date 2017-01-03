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
    
    public ArrayList<ArrayList<String>> spiltWords(ArrayList<Entry> entries){
        ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
        for (Entry entry : entries)
        {
            ArrayList<String> characters = new ArrayList<String>(Arrays.asList(entry.word.split("-")));
            returnList.add(characters);
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
        ArrayList<CharacterCount> result = new ArrayList<CharacterCount>();
        result.add(new CharacterCount("test"));

        for (ArrayList<String> characters : returnList)
        {
            boolean flag = false;
            String firstChar = characters.get(0);
            CharacterCount characterCount = new CharacterCount(firstChar);

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
}
