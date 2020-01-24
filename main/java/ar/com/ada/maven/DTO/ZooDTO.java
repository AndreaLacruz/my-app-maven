package ar.com.ada.maven.DTO;

public class ZooDTO {
    private Integer id;
    private String name;
    private String size;
    private Double budget;
    private CityDTO city;

    public ZooDTO(){
    }

    public ZooDTO(Integer id, String name, String size, Double budget, CityDTO city){
        this.id = id;
        this.name = name;
        this.size = size;
        this.budget =budget;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())return false;
        ZooDTO that = (ZooDTO) obj;
        return this.id.equals(that.id) && this.name.equals(that.name) && this.size.equals(that.size) &&
                this.budget.equals(that.budget) && this.city.equals(that.city);
    }

    @Override
    public String toString() {
        return "ZooDTO: [id:" + id + ", name: " + name + ", size: " + size + ", budget: " + budget + ", city: " + city+ "]";
    }


}

