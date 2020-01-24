package ar.com.ada.maven.DTO;

public class CountryDTO {

    private Integer id;
    private String name;
    private ContinentDTO continent;

    public CountryDTO(){}

    public CountryDTO(String name){
        this.name = name;
    }

    public CountryDTO(Integer id, String name){
        this.name = name;
        this.id = id;
    }

    public CountryDTO(Integer id, String name, ContinentDTO continent){
        this.name = name;
        this.id = id;
        this.continent = continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public ContinentDTO getContinent() {
        return continent;
    }

    public void setContinent(ContinentDTO continent) {
        this.continent = continent;
    }

    @Override
    public int hashCode() {
        return -232 * id.hashCode() + name.hashCode() + continent.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CountryDTO country = (CountryDTO) obj;
        return id.equals(country.id) && name.equals(country.name) && continent.equals(country.continent);
    }

    @Override
    public String toString() {
        return "CountryDTO : [ id: " + this.id + ", name: " + this.name + ", continent: " + this.continent + " ]";
    }
}
