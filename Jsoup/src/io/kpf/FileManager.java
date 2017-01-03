package io.kpf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Katie on 24/12/2016.
 */
public class FileManager {

    ArrayList<File> returnFileList = new ArrayList<File>();
    
    public ArrayList<File> listFilesForFolder(final File folder) {
        
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String[] split = fileEntry.getAbsolutePath().split("\\\\");
                if(!(fileEntry.getAbsoluteFile().getParent().contains("feed")) && split.length >= 12)
                {
                    returnFileList.add(fileEntry.getAbsoluteFile());
                }
            }
        }
        return returnFileList;
    }
    
    public void makeFile(ArrayList<File> fileArrayList)
    {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (File file : fileArrayList) {
            System.out.println(file.getAbsolutePath());

            Document doc = null;
            try {
                doc = Jsoup.parse(file, "UTF-8", "");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(doc != null)
            {
                //entry-title entry-content
                Elements title = doc.getElementsByClass("entry-title");
                // Khania, Knossos, Midea, Mycenae, Pylos, Thebes, Tiryns
                Elements places = doc.select("div.entry-content > p > small");
                Elements definition = doc.select("div.entry-content > h1:contains(definition) ~ p");
                String def = definition.text().replace(",", "");
                Entry newEntry = new Entry(title.text(), def);

                if(places.text().toLowerCase().contains("khania"))
                {
                    newEntry.Khania = true;
                }

                if(places.text().toLowerCase().contains("knossos"))
                {
                    newEntry.Knossos = true;
                }

                if(places.text().toLowerCase().contains("midea"))
                {
                    newEntry.Midea = true;
                }

                if(places.text().toLowerCase().contains("mycenae"))
                {
                    newEntry.Mycenae = true;
                }

                if(places.text().toLowerCase().contains("pylos"))
                {
                    newEntry.Pylos = true;
                }

                if(places.text().toLowerCase().contains("thebes"))
                {
                    newEntry.Thebes = true;
                }

                if(places.text().toLowerCase().contains("tiryns"))
                {
                    newEntry.Tiryns = true;
                }

                entries.add(newEntry);
            }
        }

        for (Entry entry : entries)
        {
            entry.print();
        }

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("C:\\Users\\Katie\\Documents\\Jsoup\\words.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(entries);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in C:\\Users\\Katie\\Documents\\Jsoup\\words.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
    
    public ArrayList<Entry> getListFromFile()
    {
        ArrayList<Entry> list;
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Katie\\Documents\\Jsoup\\words.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Entry>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
        return list;
    }
}
