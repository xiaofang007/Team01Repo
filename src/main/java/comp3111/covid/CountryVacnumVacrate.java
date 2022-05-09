package comp3111.covid;
public class CountryVacnumVacrate {

    private String countryName = null;
    private double peopleVaccinated = 0;
    private double vaccinationRate = 0;


    public CountryVacnumVacrate() {
    }

    public CountryVacnumVacrate(String countryName, double peopleVaccinated,double vaccinationRate) {
        this.countryName = countryName;
        this.peopleVaccinated = peopleVaccinated;
        this.vaccinationRate = vaccinationRate;
    }


    public String countryName() {
        return countryName;
    }

    public void setcountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public void setPeopleVaccinated(double peopleVaccinated) {
        this.peopleVaccinated = peopleVaccinated;
    }
    
    public double getVaccinationRate() {
        return vaccinationRate;
    }

    public void setVaccinationRate(double vaccinationRate) {
        this.vaccinationRate = vaccinationRate;
    }


}
