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
        return this.artifactRepository.findById(artifactId) // Try to find it, or else throw an exception
                .orElseThrow(() -> new ArtifactNotFoundException(artifactId)); // get retrieves the actual object from Optional wrapper class
    }
}
