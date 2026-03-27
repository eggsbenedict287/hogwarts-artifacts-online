package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Wizard class, wizard can own many artifacts


@Entity
public class Wizard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    // Cascade settings
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner") // How it's mapped with object relation mapping, the foreign key is given up
    private List<Artifact> artifacts = new ArrayList<>(); // Be careful! we don't want the list initialized to NULL since we can't add things to a NULL list

    public Wizard() {
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

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(Artifact artifact) {

        artifact.setOwner(this);
        this.artifacts.add(artifact);

    }

    public Integer getNumberOfArtifacts() {
        return this.artifacts.size(); // OOP principal, the calling class should not know that much about the inner working of the other class
    }

    public void removeAllArtifacts(){
        this.artifacts.stream().forEach( artifact -> artifact.setOwner(null));
        this.artifacts = null;
    }
}
