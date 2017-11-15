package org.feup.apm.testexchange;


public class RowItem {

    private int image;
    private String countryNames;
    private String Rate;

    RowItem(int image, String countryNames, String rate) {
        this.image = image;
        this.countryNames = countryNames;
        this.Rate = rate;
    }

    int getImage() {
        return(image);
    }

    public void setImage(int image) {
        this.image=image;
    }

    String getCountryNames() {
        return(countryNames);
    }

    public void setCountryNames(String countryNames) {
        this.countryNames=countryNames;
    }

    String getRate() {
        return(Rate);
    }

    void setRate(String Rate) {
        this.Rate=Rate;
    }


    public String toString() {
        return(getCountryNames());
    }

}

