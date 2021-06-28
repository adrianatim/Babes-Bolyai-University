package webubb.domain;

public class Team {
    private int id;
    private int capitanId;
    private String name;
    private String description;
    private String members;

    public Team(int id, int capitanId, String name, String description, String members) {
        this.id = id;
        this.capitanId = capitanId;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapitanId() {
        return capitanId;
    }

    public void setCapitanId(int capitanId) {
        this.capitanId = capitanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}
