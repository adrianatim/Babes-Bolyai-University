package webubb.domain;

public class FamilyRelation {

    private int id;
    private String username;
    private String mother;
    private String father;

    public FamilyRelation(int id, String username, String mother, String father) {
        this.id = id;
        this.username = username;
        this.mother = mother;
        this.father = father;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
