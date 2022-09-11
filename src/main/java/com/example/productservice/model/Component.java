package main.java.com.example.productservice.model;

public class Component {

    private Long componentId;
    private String name;
    private double price;
    private double height;
    private double weight;
    private String color;
    private String countryOfOrigin;
    private String grade;
    private String category;
    private String classification;
    private String harvestSeason;

    public Component(String name, double price, double height, double weight, String color, String countryOfOrigin, String grade, String category, String classification, String harvestSeason) {
        this.name = name;
        this.price = price;
        this.height = height;
        this.weight = weight;
        this.color = color;
        this.countryOfOrigin = countryOfOrigin;
        this.grade = grade;
        this.category = category;
        this.classification = classification;
        this.harvestSeason = harvestSeason;
    }

    public Component() {
    }

    public Long getComponentId() {
        return componentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getHarvestSeason() {
        return harvestSeason;
    }

    public void setHarvestSeason(String harvestSeason) {
        this.harvestSeason = harvestSeason;
    }

}
