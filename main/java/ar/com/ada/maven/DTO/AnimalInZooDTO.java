package ar.com.ada.maven.DTO;

public class AnimalInZooDTO {

    private ZooDTO zoo;
    private AnimalDTO animal;

    public AnimalInZooDTO(){}

    public AnimalInZooDTO(AnimalDTO animal, ZooDTO zoo){
        this.animal = animal;
        this.zoo = zoo;
    }

    public AnimalDTO getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDTO animal) {
        this.animal = animal;
    }

    public void setZoo(ZooDTO zoo) {
        this.zoo = zoo;
    }

    public ZooDTO getZoo() {
        return zoo;
    }

    @Override
    public String toString() {
        return "Animal in Zoo: [Animal: " + animal + ", Zoo: " + zoo + "]";
    }
}
