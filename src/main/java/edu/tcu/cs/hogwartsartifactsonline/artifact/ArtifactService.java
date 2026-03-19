package edu.tcu.cs.hogwartsartifactsonline.artifact;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional // Database Transaction, puts every method as a transaction, if method throws exception, rollback occurs
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    public ArtifactService(ArtifactRepository artifactRepository) { // How we inject dependencies
        this.artifactRepository = artifactRepository;
    }

    public Artifact findById(String artifactId){
        return null;
    }
}
