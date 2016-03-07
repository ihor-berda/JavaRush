package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by IGOR on 29.12.2015.
 */
public class HtmlView implements View
{
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    private Controller controller;

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String result = null;
        try {
            Document document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element copy = element.clone();
            copy.removeClass("template").removeAttr("style");
            document.getElementsByAttributeValue("class", "vacancy").remove();
            for (Vacancy vacancy : vacancies) {
                Element c = copy.clone();
                c.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                c.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                c.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = c.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(c.outerHtml());
            }
            result = document.html();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return result;
    }

    private void updateFile(String data) {
        try {
            PrintWriter fileWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            fileWriter.write(data);
            fileWriter.close();
        }
        catch (Exception e) {}
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}
