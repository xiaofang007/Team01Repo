package comp3111.covid;

public class CountryVacrateDateRange {

    private String countryName = null;
    private String date= null;
    private Double vaccinationRate = 0.0;


    public CountryVacrateDateRange() {
    }

    public CountryVacrateDateRange(String countryName,String date, Double vaccinationRate) {
        this.countryName = countryName;
        this.date = date;
        this.vaccinationRate = vaccinationRate;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public double getVaccinationRate() {
        return vaccinationRate;
    }

    public void setVaccinationRate(Double vaccinationRate) {
        this.vaccinationRate = vaccinationRate;
    }


}
