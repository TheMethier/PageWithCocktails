package com.example.verylastapi.controllers;
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

public class ScraperController {
    public List<Cocktail> ScrapMyCocktail() throws IOException {

        List<Cocktail> list=new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://mojbar.pl/drinki-z-tequili/").get();
            Elements hrefs = doc.getElementsByClass("entry-title td-module-title");
            for (Element href :
                 hrefs) {
                String nameo = null;
                String description=null;

                System.out.println(href.children().attr("href"));
                Document pages= Jsoup.connect(href.children().attr("href")).get();
                Elements titles= pages.getElementsByClass("tdb-title-text");
                for (Element tit:
                     titles) {

                    nameo=(tit.text());
                }
                Elements descAndpre =pages.getElementsByClass("tdb-block-inner td-fix-index");
                for (Element desc:
                     descAndpre) {
                    if (!desc.getElementsByTag("p").isEmpty()) {
                       // System.out.println(desc.getElementsByTag("p").first().text());
                        description=(desc.getElementsByTag("p").first().text());
                    }
                    Set<Ingredients> Indi = new HashSet<>();

                    Cocktail cocktail=new Cocktail(nameo,description,"",Indi);

                    if (!desc.getElementsByTag("li").isEmpty()) {

                        for (Element o : desc.getElementsByTag("li")) {
                            /*System.out.println(o.text().replaceAll("ml","").replaceAll("[0-9]+", "").replaceAll("-",""));*/
                            String name = o.text().replaceAll("ml", "").replaceAll("[0-9]+", "").replaceAll("-", "");
                            String unit = new String();
                            float quantity = 0;
                            Pattern pattern = Pattern.compile("[0-9]+");//napraw regex
                            Matcher matcher = pattern.matcher(o.text());
                            if (o.text().contains("ml")) {
                                while (matcher.find()) {
                                    quantity = Float.parseFloat(matcher.group());

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
                            System.out.println(cocktail.getName());
                            //Add quantity
                            Ingredients p = new Ingredients(cocktail, name, quantity, unit);
                            Indi.add(p);
                        }

                        list.add(cocktail);

                    }

                }


                System.out.println("."+"\n");
            /*    cocktail.getIngredients().forEach(ingredients -> {
                    System.out.println(ingredients.getQuantity()+" "+ ingredients.getUnit()+" "+ingredients.getName());
                });*/
            }
            System.out.println("Complete!");
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
            Document doc = Jsoup.connect("https://mojbar.pl/drinki-z-tequili/").get();
            Elements hrefs = doc.getElementsByClass("entry-title td-module-title");
            int i=-1;
            for (Element href :
                    hrefs) {
                System.out.println(href.children().attr("href"));
                Document pages= Jsoup.connect(href.children().attr("href")).get();
                Elements titles= pages.getElementsByClass("tdb-title-text");
                Elements descAndpre =pages.getElementsByClass("tdb-block-inner td-fix-index");
                for (Element desc:
                        descAndpre) {
                    if (!desc.getElementsByTag("li").isEmpty()) {
                        i++;
                        for (Element o : desc.getElementsByTag("li")) {
                            /*System.out.println(o.text().replaceAll("ml","").replaceAll("[0-9]+", "").replaceAll("-",""));*/
                            String name = o.text().replaceAll("ml", "").replaceAll("[0-9]+", "").replaceAll("-", "");
                            String unit = new String();
                            float quantity = 0;
                            Pattern pattern = Pattern.compile("[0-9]+");//napraw regex
                            Matcher matcher = pattern.matcher(o.text());
                            if (o.text().contains("ml")) {
                                while (matcher.find()) {
                                    quantity = Float.parseFloat(matcher.group());

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
                            //Add quantity
                            Ingredients p = new Ingredients(cocktails.get(i), name, quantity, unit);
                            list.add(p);
                        }

                    }

                }


                System.out.println("."+"\n");
            /*    cocktail.getIngredients().forEach(ingredients -> {
                    System.out.println(ingredients.getQuantity()+" "+ ingredients.getUnit()+" "+ingredients.getName());
                });*/
            }
            System.out.println("Complete!");
        }
        catch (Exception e)
        {

            System.out.println(e);
        }
        return list;
    }
    }

