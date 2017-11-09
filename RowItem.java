package org.feup.apm.testexchange;


class RowItem {

    int image;
    String countryNames;
    String Rate;

    RowItem(int image, String countryNames, String rate) {
        this.image = image;
        this.countryNames = countryNames;
        this.Rate = rate;
    }

    public int getImage() {
        return(image);
    }

    public void setImage(int image) {
        this.image=image;
    }

   public  String getCountryNames() {
        return(countryNames);
    }

    public void setCountryNames(String countryNames) {
        this.countryNames=countryNames;
    }

    public String getRate() {
        return(Rate);
    }

    public void setRate(String Rate) {
        this.Rate=Rate;
    }


    public String toString() {
        return(getCountryNames());
    }

}

