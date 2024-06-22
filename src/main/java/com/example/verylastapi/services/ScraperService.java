package com.example.verylastapi.services;
import com.example.verylastapi.classes.models.Cocktail;
import com.example.verylastapi.classes.models.Ingredient;
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
   private List<Cocktail> list=new ArrayList<>();

    public List<Cocktail> ScrapMyCocktail()  {
        List<String> url=new ArrayList<>();
        try {
            for(int i=1;i<6;i++)
            {
                String link = "https://mojbar.pl/drinki-z-likieru/page/" + i;
                Document startPage = Jsoup.connect(link).get();
                extractUrl(startPage,url);
                Elements cocktailDetails = startPage.getElementsByClass("entry-title td-module-title");
                for (Element cocktailDetail :
                        cocktailDetails) {
                    Cocktail cocktail= new Cocktail();
                    Document cocktailPage = Jsoup.connect(cocktailDetail.children().attr("href")).get();
                    extractCocktailName(cocktailPage,cocktail);
                    extractDescription(cocktailPage,cocktail,url);
                    list.add(cocktail);
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
                Document startPage = Jsoup.connect(link).get();
                Elements cocktailDetails = startPage.getElementsByClass("entry-title td-module-title");
                for (Element cocktail :
                        cocktailDetails) {
                    System.out.println(cocktail.children().attr("href"));
                    Document cocktailPage = Jsoup.connect(cocktail.children().attr("href")).get();
                    Elements descriptions = cocktailPage.getElementsByClass("tdb-block-inner td-fix-index");
                    i++;
                    for (Element description :
                            descriptions) {
                        float capacity=0;
                        StringBuilder alktag = new StringBuilder();//dodaj asercję na więcej wariantów przepisu w jednym artykule
                        if (!description.getElementsByTag("li").isEmpty())
                        {
                            for (Element item : description.getElementsByTag("li")) {
                                Ingredient ingredient = new Ingredient(cocktails.get(i));
                                capacity=extractIngredient(item,capacity,ingredient);
                                InsertNameTagIntoAlktag(alktag,ingredient.getName());
                                list.add(ingredient);
                            }
                            InsertCapacityTagIntoAlktag(alktag,capacity);
                            cocktails.get(i).setTag(alktag.toString());
                        }
                    }
                }
                a=7;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return list;
    }
    private void extractCocktailName(Document document, Cocktail cocktail)
    {
        Elements titles = document.getElementsByClass("tdb-title-text");
        for (Element tit :
                titles) {
            String cocktailName = tit.text()
                    .replace(" – Przepis Na Drink", "")
                    .replace(" – przepis na drink", "")
                    .replace(" – Przepis na Drink", "");
            cocktail.setName(cocktailName);
        }
    }
    private void extractUrl(Document document, List<String> url)
    {
        Elements images = document.getElementsByClass("entry-thumb td-thumb-css ");
        for (Element image : images) {
            String imageUrl = image
                    .attr("style")
                    .replace("background-image: url(", "").replace(")", "");
            url.add(imageUrl);
        }
    }
    private void extractDescription(Document document, Cocktail cocktail, List<String> url )
    {
        Elements descriptions = document.getElementsByClass("tdb-block-inner td-fix-index");
        for (Element desc :
                descriptions) {
            if (!desc.getElementsByTag("p").isEmpty())
            {
                cocktail.setDescription(Objects.requireNonNull(desc.getElementsByTag("p").first()).text());
                cocktail.setPrep("Wszystkie składniki wstrząśnij w szejkerze z lodem i odcedź do schłodzonego szkła.\n");
                cocktail.setImageUrl(url.remove(0));
            }
        }
    }

    private void extractIngredientName (String text, Ingredient ingredient)
    {
        String name = text
                .replaceAll("ml", "")
                .replaceAll("[0-9]+", "")
                .replaceAll("-", "");
        ingredient.setName(name);
    }
    private float extractIngredient(Element o, float capacity, Ingredient ingredient)
    {
        extractIngredientName(o.text(),ingredient);
        ingredient.setUnit("");
        Pattern pattern = Pattern.compile("[0-9]+");//napraw regex
        Matcher matcher = pattern.matcher(o.text());
        List<String> units = Arrays.asList("ml","dashe", "dash");
        for(String unit : units) {
            if (o.text().contains(unit)) {
                ingredient.setUnit(unit);
            }
        }
        while (matcher.find()) {
            ingredient.setQuantity(Float.parseFloat(matcher.group()));
            capacity = (ingredient.getUnit() == "ml") ? capacity + ingredient.getQuantity() : capacity;
        }
        return capacity;
    }
    private  void InsertNameTagIntoAlktag(StringBuilder alktag, String name) {
        Map<String, String> matches =new HashMap<>( Map.of(
                "ódk", "w",
                "rum", "r",
                "wh", "h",
                "teq", "t",
                "Teq", "t",
                "gin", "g"
        ));
        matches.forEach((x,y)->{
            if (name.contains(x) && !alktag.toString().contains(y)) {
                alktag.append(y);
            }
        });
    }
    private void InsertCapacityTagIntoAlktag(StringBuilder alktag, float capacity) {
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
    }
}

