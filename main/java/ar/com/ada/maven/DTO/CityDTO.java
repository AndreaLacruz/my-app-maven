package ar.com.ada.maven.DTO;

public class CityDTO {

    private Integer id;
    private String name;
    private CountryDTO country;

    public CityDTO(){}

    public CityDTO(String name){
        this.name = name;
    }

    public CityDTO(Integer id, String name){
       this.name = name;
       this.id = id;
    }

    public CityDTO(Integer id, String name, CountryDTO country){
        this.name = name;
        this.id = id;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId (Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CityDTO: [ id: " + id + ", name: " + name + ", country: " + country + " ]";
    }

    @Override
    public int hashCode() {
        return -23 * id.hashCode() + name.hashCode() + country.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CityDTO that = (CityDTO) obj;
    return this.id.equals(that.id) && this.name.equals(that.name) && this.country.equals(that.country);

     }
}


