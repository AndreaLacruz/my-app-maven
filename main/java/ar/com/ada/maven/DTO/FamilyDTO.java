package ar.com.ada.maven.DTO;

public class FamilyDTO {

    private Integer id;
    private String name;

    public FamilyDTO(){}

    public FamilyDTO(String name){
        this.name = name;
    }

    public FamilyDTO(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return -22 * id.hashCode() + name.hashCode();
    }

    @Override
    public String toString() {
        return "FamilyDTO: [id: " + id + ", name: " + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FamilyDTO that = (FamilyDTO) obj;
        return this.id.equals(that.id) && this.name.equals(that.name);
    }
}
