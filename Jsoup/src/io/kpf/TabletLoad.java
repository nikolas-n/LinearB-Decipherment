package io.kpf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Katie on 11/01/2017.
 */
public class TabletLoad {
    public TabletLoad() {
        FileManager fileManager = new FileManager();

        File tabletFolder = new File("D:\\Documents\\University\\Year 3\\FYP\\linearBCorpus\\Linear B\\minoan.deaditerranean.com\\linear-b-transliterations");

        ArrayList<File> fileArrayList = fileManager.listFilesForFolder(tabletFolder);


        ArrayList<String> ideograms = new ArrayList<String>()
        {
            {
                add("AES");
                add("ARB");
                add("ARM");
                add("AROM");
                add("AUR");
                add("BIG");
                add("BOS");
                add("CAP");
                add("CORN");
                add("CROC");
                add("CUR");
                add("CYP");
                add("EQU");
                add("FAR");
                add("GRA");
                add("GUP");
                add("HAS");
                add("HORD");
                add("L");
                add("LANA");
                add("LUNA");
                add("M");
                add("MUL");
                add("N");
                add("OLE");
                add("OLIV");
                add("OVIS");
                add("P");
                add("PUG");
                add("PYC");
                add("Q");
                add("ROTA");
                add("S");
                add("SAG");
                add("SUS");
                add("T");
                add("TELA");
                add("TUN");
                add("V");
                add("VIN");
                add("VIR");
                add("Z");
                add("\\*142");
                add("\\*146");
                add("\\*150");
                add("\\*153");
                add("\\*154");
                add("\\*158");
                add("\\*161");
                add("\\*164");
                add("\\*165");
                add("\\*166");
                add("\\*167");
                add("\\*168");
                add("\\*169");
                add("\\*170");
                add("\\*171");
                add("\\*172");
                add("\\*174");
                add("\\*180");
                add("\\*181");
                add("\\*188");
                add("\\*189");
                add("\\*190");
                add("\\*202-VAS");
                add("\\*204-VAS");
                add("\\*207-VAS");
                add("\\*209-VAS");
                add("\\*210-VAS");
                add("\\*211-VAS");
                add("\\*212-VAS");
                add("\\*213-VAS");
                add("\\*214-VAS");
                add("\\*217-VAS");
                add("\\*218-VAS");
                add("\\*221-VAS");
                add("\\*222-VAS");
                add("\\*226-VAS");
                add("\\*227-VAS");
                add("\\*229-VAS");
                add("\\*234");
                add("\\*255");
                add("\\*305-VAS");
                add("LANA");
            }
        };

        ArrayList<String> listOfSigns = new ArrayList<String>(){
            {

                add("a2");
                add("a3");
                add("dwe");
                add("dwo");
                add("nwa");
                add("pte");
                add("pu2");
                add("ra2");
                add("ra3");
                add("ro2");
                add("ra2");
                add("twe");
                add("two");
                add("au");
                add("da");
                add("de");
                add("di");
                add("do");
                add("du");
                add("ja");
                add("je");
                add("jo");
                add("ju");
                add("ka");
                add("ke");
                add("ki");
                add("ko");
                add("ku");
                add("ma");
                add("me");
                add("mi");
                add("mo");
                add("mu");
                add("na");
                add("ne");
                add("ni");
                add("no");
                add("nu");
                add("pa");
                add("pe");
                add("po");
                add("pi");
                add("pu");
                add("qa");
                add("qo");
                add("qe");
                add("qi");
                add("qo");
                add("qu");
                add("ra");
                add("re");
                add("ri");
                add("ro");
                add("ru");
                add("sa");
                add("se");
                add("so");
                add("si");
                add("su");
                add("ta");
                add("te");
                add("ti");
                add("to");
                add("tu");
                add("wa");
                add("we");
                add("wi");
                add("wo");
                add("za");
                add("ze");
                add("zi");
                add("zo");
                add("zu");
                add("\\*18");
                add("\\*19");
                add("\\*22");
                add("\\*34");
                add("\\*35");
                add("\\*47");
                add("\\*49");
                add("\\*56");
                add("\\*63");
                add("\\*64");
                add("\\*65");
                add("\\*79");
                add("\\*82");
                add("\\*83");
                add("\\*86");
                add("a");
                add("e");
                add("i");
                add("o");
                add("u");
            }
        };

        ArrayList<Tablet> listOfTablets = new ArrayList<Tablet>();
        for (File file : fileArrayList) {
            try {
                Document doc = Jsoup.parse(file, "UTF-8");

                Elements names = doc.getElementsByTag("h2");
                Elements paragraph = doc.getElementsByTag("p");

                // .A da-mi-ni-jo , OVISm 30
                //.B a-nu-ko / ku-ta-to , pe OVISm 20

                //da-mi-ni-jo , OVIS / a-nu-ko , ku-ta-to , pe , OVIS
                for (Element name : names) {
                    if (name.text().contains("KN")|| name.text().contains("PY")){
                            //|| name.text().contains("KH") || name.text().contains("MY")
                             //|| name.text().contains("TH") || name.text().contains("TI")) {

                        Element p = name.nextElementSibling();

                        String[] title = name.text().split(" ");
                        //System.out.println(name.text());

                        String content = p.text();

                        String[] remove = content.split(": ");
                        if(remove.length > 1)
                        {
                            content = remove[1];
                        }

                        //KN As
                        System.out.println(content);

                        String list = String.join("|", listOfSigns);
                        String ideoList = String.join("|", ideograms);
                        //crazy regex
                        String missing = "(?:\\[|\\]|\\[\\.*\\])";
                        String seperator = "(?:-|" + missing + ")";
                        String sign = "\\(?(?:"+list+"|"+missing+")\\)?";

                        String word = sign + "(?:"+ seperator + sign+ ")+";
                        String wordWithoutIdeo = "" + word + "\\s*(?:,\\s*)?(?:" + ideoList +")";

                        Pattern quentinsregex = Pattern.compile("" + wordWithoutIdeo + "(?:\\|" + wordWithoutIdeo +  ")*");
                       // System.out.println(quentinsregex);

                        Matcher katiesMatcher = quentinsregex.matcher(content);

                        String inscription = "";
                        while(katiesMatcher.find())
                        {
                            Pattern stripRegex = Pattern.compile(word);
                            Matcher stripMatcher = stripRegex.matcher(katiesMatcher.group(0));
                            if(stripMatcher.find())
                            {
                                inscription = inscription + stripMatcher.group(0)+ " , ";
                            }
                        }


                        Tablet newTablet = new Tablet("", title[0], title[1], name.text());
                        newTablet.inscription = inscription ;
                        newTablet.original = content;
                        if(!"".equals(newTablet.inscription)) {
                            listOfTablets.add(newTablet);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Tablet tablet: listOfTablets
                ) {
            System.out.println(tablet.identifier + " was found at " + tablet.location + " and belongs to series " + tablet.series);
            System.out.println("   " + tablet.inscription);
            System.out.println("RAW: " + tablet.original);
            System.out.println("");
        }

        /*
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("tablets.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfTablets);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }*/

        String csvFile = "D:\\Documents\\University\\Year 3\\FYP\\LinearB-Decipherment\\Jsoup\\tabletsthatcontainnouns.csv";
        FileWriter writer = null;
        try {
            writer = new FileWriter(csvFile);
            CSVUtils.writeLine(writer, Arrays.<String>asList("identifier", "location", "series", "inscription", "original"), ';');
            for (Tablet tablet : listOfTablets) {
                CSVUtils.writeLine(writer, Arrays.<String>asList(tablet.identifier, tablet.location, tablet.series,
                        tablet.inscription, tablet.original), ';');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
