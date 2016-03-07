package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT =
            "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;

        while (true) {
            Document doc = null;
            try {
                doc = getDocument(searchString, page++);
            } catch (IOException ignore) {
            }

            Elements vacEl = doc.select("[data-qa=vacancy-serp__vacancy]");
            if (vacEl.size() == 0)
                break;

            for (Element element : vacEl) {

                Vacancy vacancy = new Vacancy();

                Element title = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                vacancy.setTitle(title.text());

                Element salary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                if (salary != null)
                    vacancy.setSalary(salary.text());
                else
                    vacancy.setSalary("");

                Element city = element.select("[data-qa=vacancy-serp__vacancy-address]").first();
                vacancy.setCity(city.text());

                Element companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                vacancy.setCompanyName(companyName.text());

                vacancy.setSiteName("http://hh.ua");

                String url = element.select("[data-qa=vacancy-serp__vacancy-title]").first().attr("href");
                vacancy.setUrl(url);

                vacancies.add(vacancy);
            }
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).
                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0").referrer("").get();
        } catch (IOException ignore) {
        }
        return doc;
    }
}



