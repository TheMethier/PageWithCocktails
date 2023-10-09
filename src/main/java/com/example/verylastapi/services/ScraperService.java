package com.example.verylastapi.services;
import com.example.verylastapi.classes.Cocktail;
import com.example.verylastapi.classes.Ingredient;
import  org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class ScraperService {
    public List<Cocktail> ScrapMyCocktail()  {
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
                    String description;
                    Document pages = Jsoup.connect(href.children().attr("href")).get();
                    Elements titles = pages.getElementsByClass("tdb-title-text");
                    for (Element tit :
                            titles) {
                        nameo = (tit.text().replace(" – Przepis Na Drink", "").replace(" – przepis na drink", "").replace(" – Przepis na Drink", ""));
                    }
                    Elements descAndpre = pages.getElementsByClass("tdb-block-inner td-fix-index");
                    for (Element desc :
                            descAndpre) {
                        if (!desc.getElementsByTag("p").isEmpty()) {
                            description = (Objects.requireNonNull(desc.getElementsByTag("p").first()).text());
                            Set<Ingredient> Indi = new HashSet<>();
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
    public List<Ingredient> ScrapMyI(List<Cocktail> cocktails) {
        List<Ingredient> list=new ArrayList<>();
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
                    Elements descAndpre = pages.getElementsByClass("tdb-block-inner td-fix-index");
                    i++;
                    for (Element desc :
                            descAndpre) {
                        float capacity=0;
                        StringBuilder alktag = new StringBuilder();//dodaj asercję na więcej wariantów przepisu w jednym artykule
                        if (!desc.getElementsByTag("li").isEmpty()) {
                            for (Element o : desc.getElementsByTag("li")) {
                                String name = o.text().replaceAll("ml", "").replaceAll("[0-9]+", "").replaceAll("-", "");
                                String unit;
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

                                if (name.contains("ódk") && !alktag.toString().contains("w")) {
                                    alktag.append("w");
                                }
                                if ((name.contains("teq") || name.contains("Teq")) && !alktag.toString().contains("t")) {

                                    alktag.append("t");
                                }
                                if (name.contains("gin") && !name.contains("ging") && !alktag.toString().contains("g")) {

                                    alktag.append("g");
                                }
                                if (name.contains("wh") && !alktag.toString().contains("h")) {
                                    alktag.append("h");
                                }
                                if (name.contains("rum") && !alktag.toString().contains("r")) {
                                    alktag.append("r");
                                }
                                Ingredient p = new Ingredient(cocktails.get(i), name, quantity, unit);
                                list.add(p);
                            }
                            if(capacity<=50)
                            {
                                alktag.append("S");
                            }
                            else if(capacity<=120)
                            {
                                alktag.append("M");
                            }
                            else {
                                alktag.append("L");
                            }
                            cocktails.get(i).setTag(alktag.toString());
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

