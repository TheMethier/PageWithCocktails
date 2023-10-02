package com.example.verylastapi.services;
import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredients;
import  org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.TileObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScraperService {
    public List<Cocktail> ScrapMyCocktail() throws IOException {
        List<Cocktail> list=new ArrayList<>();
        List<String> url=new ArrayList<>();
        try {
            for(int i=1;i<6;i++)
            {
                String link = "https://mojbar.pl/drinki-z-likieru/page/" + i;
                Document doc = Jsoup.connect(link).get();
                Elements images = doc.getElementsByClass("entry-thumb td-thumb-css ");
                for (Element image : images) {
                    String urli = image.attr("style").replace("background-image: url(", "").replace(")", "");
                    url.add(urli);
                }
                Elements hrefs = doc.getElementsByClass("entry-title td-module-title");
                for (Element href :
                        hrefs) {
                    String nameo = null;
                    String description = null;
                    String r = null;
                    Document pages = Jsoup.connect(href.children().attr("href")).get();
                    Elements titles = pages.getElementsByClass("tdb-title-text");
                    int u = 0;
                    u++;
                    for (Element tit :
                            titles) {


                        nameo = (tit.text().replace(" – Przepis Na Drink", "").replace(" – przepis na drink", "").replace(" – Przepis na Drink", ""));
                    }
                    Elements descAndpre = pages.getElementsByClass("tdb-block-inner td-fix-index");
                    for (Element desc :
                            descAndpre) {
                        if (!desc.getElementsByTag("p").isEmpty()) {
                            description = (desc.getElementsByTag("p").first().text());
                            Set<Ingredients> Indi = new HashSet<>();
                            Cocktail cocktail = new Cocktail(nameo, description, "", Indi, "Wszystkie składniki wstrząśnij w szejkerze z lodem i odcedź do schłodzonego szkła.\n", "");
                            String Url = url.remove(0);
                            cocktail.setImageUrl(Url);
                            list.add(cocktail);
                            System.out.println(cocktail.getName());
                        }

                    }
            
                }
                System.out.println("Complete "+i+" !");
                i=7;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return list;
    }
    public List<Ingredients> ScrapMyI(List<Cocktail> cocktails) throws IOException {
        List<Ingredients> list=new ArrayList<>();
        try {
            int i=-1;
            for(int a=1;a<6;a++)
            {
                String link = "https://mojbar.pl/drinki-z-likieru/page/" + a;
                Document doc = Jsoup.connect(link).get();
                Elements hrefs = doc.getElementsByClass("entry-title td-module-title");
                for (Element href :
                        hrefs) {
                    System.out.println(href.children().attr("href"));
                    Document pages = Jsoup.connect(href.children().attr("href")).get();
                    Elements titles = pages.getElementsByClass("tdb-title-text");
                    Elements descAndpre = pages.getElementsByClass("tdb-block-inner td-fix-index");
                    i++;
                    for (Element desc :
                            descAndpre) {
                        float capacity=0;
                        String alktag = new String("");//dodaj asercję na więcej wariantów przepisu w jednym artykule
                        if (!desc.getElementsByTag("li").isEmpty()) {
                            for (Element o : desc.getElementsByTag("li")) {
                                String name = o.text().replaceAll("ml", "").replaceAll("[0-9]+", "").replaceAll("-", "");
                                String unit = new String();
                                float quantity = 0;
                                Pattern pattern = Pattern.compile("[0-9]+");//napraw regex
                                Matcher matcher = pattern.matcher(o.text());
                                if (o.text().contains("ml")) {
                                    while (matcher.find()) {
                                        quantity = Float.parseFloat(matcher.group());
                                        capacity+=quantity;
                                    }
                                    unit = "ml";
                                } else if (o.text().contains("dashe")) {
                                    System.out.println("dashe");
                                    unit = "dashe";
                                    while (matcher.find()) {
                                        quantity = Float.parseFloat(matcher.group());
                                    }
                                } else {
                                    unit = "";
                                    while (matcher.find()) {
                                        quantity = Float.parseFloat(matcher.group());
                                    }
                                }

                                if (name.contains("ódk") && !alktag.contains("w")) {
                                    alktag += "w";
                                }
                                if ((name.contains("teq") || name.contains("Teq")) && !alktag.contains("t")) {

                                    alktag += "t";
                                }
                                if (name.contains("gin") && !name.contains("ging") && !alktag.contains("g")) {

                                    alktag += "g";
                                }
                                if (name.contains("wh") && !alktag.contains("h")) {
                                    alktag += "h";
                                }
                                if (name.contains("rum") && !alktag.contains("r")) {
                                    alktag += "r";
                                }
                                System.out.println(i);
                                Ingredients p = new Ingredients(cocktails.get(i), name, quantity, unit);
                                list.add(p);
                            }
                            if(capacity<=50)
                            {
                                alktag+="S";
                            }
                            else if(capacity<=120)
                            {
                                alktag+="M";
                            }
                            else {
                                alktag+="L";
                            }
                            cocktails.get(i).setTag(alktag);
                        }

                    }
                }
                System.out.println("Complete!");
                a=7;
            }
            }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return list;
    }
    }

