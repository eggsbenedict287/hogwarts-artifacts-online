package edu.tcu.cs.hogwartsartifactsonline.artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Optional just for consistency
public interface ArtifactRepository extends JpaRepository<Artifact, String> { // We just implement the jpa repository
}
