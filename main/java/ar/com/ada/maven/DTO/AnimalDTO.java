package ar.com.ada.maven.DTO;

import java.util.Date;

public class AnimalDTO {
    private Integer id;
    private String sex;
    private Date birthday;
    private CountryDTO country;
    private SpeciesDTO species;

    public AnimalDTO(){}

    public AnimalDTO(Integer id, String sex, Date birthday, CountryDTO country, SpeciesDTO species){
        this.sex = sex;
        this.id = id;
        this.birthday = birthday;
        this.country = country;
        this.species = species;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public SpeciesDTO getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDTO species) {
        this.species = species;
    }

    @Override
    public int hashCode() {
        return -112 * id.hashCode() + sex.hashCode() + birthday.hashCode() + species.hashCode() + country.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        AnimalDTO that = (AnimalDTO) obj;
        return this.id.equals(that.id) && this.sex.equals(that.sex) && this.birthday.equals(that.birthday) &&
                this.species.equals(that.species) && this.equals(that);
    }
}
