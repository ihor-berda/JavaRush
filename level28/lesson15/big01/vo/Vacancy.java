package com.javarush.test.level28.lesson15.big01.vo;

public class Vacancy {
    private String title;
    private String salary;
    private String city;
    private String companyName;
    private String siteName;
    private String url;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vacancy vacancy = (Vacancy) o;

        if (city != null ? !city.equals(vacancy.city) : vacancy.city != null)
            return false;
        if (companyName != null ? !companyName.equals(vacancy.companyName) : vacancy.companyName != null)
            return false;
        if (salary != null ? !salary.equals(vacancy.salary) : vacancy.salary != null)
            return false;
        if (siteName != null ? !siteName.equals(vacancy.siteName) : vacancy.siteName != null)
            return false;
        if (title != null ? !title.equals(vacancy.title) : vacancy.title != null)
            return false;
        if (url != null ? !url.equals(vacancy.url) : vacancy.url != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
