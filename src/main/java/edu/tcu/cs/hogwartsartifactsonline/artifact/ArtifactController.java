package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import edu.tcu.cs.hogwartsartifactsonline.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtifactController {

    private final ArtifactService artifactService;

    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @GetMapping("/api/v1/artifacts/{artifactId}") // implements API endpoint
    public Result findArtifactById(@PathVariable String artifactId){ //@PathVariable binds these two variables
        Artifact foundArtifact =  this.artifactService.findById(artifactId);
        return new Result(true, StatusCode.SUCCESS,"Find One Success", foundArtifact);
        // Note the spring API automagically serializes a java object to a JSON to front end
    }

}
