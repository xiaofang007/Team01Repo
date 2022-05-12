package comp3111.covid;
public class CountryVacnumVacrate {

    private String countryName = null;
    private Double peopleVaccinated = 0.0;
    private Double vaccinationRate = 0.0;


    public CountryVacnumVacrate() {
    }

    public CountryVacnumVacrate(String countryName, Double peopleVaccinated,Double vaccinationRate) {
        this.countryName = countryName;
        this.peopleVaccinated = peopleVaccinated;
        this.vaccinationRate = vaccinationRate;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public void setPeopleVaccinated(Double peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }
    
    public double getVaccinationRate() {
        return vaccinationRate;
    }

    public void setVaccinationRate(Double vaccinationRate) {
        this.vaccinationRate = vaccinationRate;
    }


}
