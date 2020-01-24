package ar.com.ada.maven.DTO;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class ContinentDTO {

    private Integer id;
    private String name;

    public ContinentDTO() {
    }

    public ContinentDTO(String name) {
        this.name = name;
    }

    public ContinentDTO(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ContinentDTO continent = (ContinentDTO) obj;

        return id.equals(continent.id) && name.equals(continent.name);
    }

    @Override
    public String toString() {
        return "ContinentDTO: [id:" + this.id + ", name: " + this.name + "]";
    }

    @Override
    public int hashCode() {
        return -233 * id.hashCode() + name.hashCode() ;
    }
}