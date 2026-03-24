package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional // Database Transaction, puts every method as a transaction, if method throws exception, rollback occurs
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    private final IdWorker idWorker;

    public ArtifactService(ArtifactRepository artifactRepository, IdWorker idWorker) {
        this.artifactRepository = artifactRepository;
        this.idWorker = idWorker;
    }

    public Artifact findById(String artifactId){
        return this.artifactRepository.findById(artifactId) // Try to find it, or else throw an exception
                .orElseThrow(() -> new ArtifactNotFoundException(artifactId)); // get retrieves the actual object from Optional wrapper class
    }

    public List<Artifact> findAll(){
        return this.artifactRepository.findAll();
    }

    public Artifact save(Artifact newArtifact){

        newArtifact.setId(idWorker.nextId() + ""); // Generate the ID, and then we simply convert it to String with a concat
        return this.artifactRepository.save(newArtifact); // The ownter will be NULL
    }
}
