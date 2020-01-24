package ar.com.ada.maven.DTO;

public class SpeciesDTO {

    private Integer id;
    private String commonName;
    private String scientificName;
    private Boolean endangered;
    private FamilyDTO family;

    public SpeciesDTO(){}

    public SpeciesDTO(Integer id, String commonName, String scientificName, Boolean endangered, FamilyDTO family){
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.endangered = endangered;
        this.family = family;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getEndangered() {
        return endangered;
    }

    public void setFamily(FamilyDTO family) {
        this.family = family;
    }

    public void setEndangered(Boolean endangered) {
        this.endangered = endangered;
    }

    public FamilyDTO getFamily() {
        return family;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpeciesDTO that = (SpeciesDTO) obj;
        return this.id.equals(that.id) && this.commonName.equals(that.commonName) && this.family.equals(that.family) &&
                this.scientificName.equals(that.scientificName) && this.endangered.equals(that.endangered);
    }

    @Override
    public String toString() {
        return "Species: [id: " + id + ", common name: " + commonName + ", Scientific name: " + scientificName +
                ", endangered: " + endangered + ", family: " + family + "]";
    }
}
