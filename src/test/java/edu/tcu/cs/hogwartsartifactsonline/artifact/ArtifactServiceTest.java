package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Using mock a class, simulate a class
// Test class
class ArtifactServiceTest {

    @Mock
    ArtifactRepository artifactRepository; // Were simulating this

    @Mock
    IdWorker idWorker; // Another mock to simulate the id generator



    @InjectMocks // How we inject the dependencies
    ArtifactService artifactService; // Class under tests


    List<Artifact> artifacts;
    @BeforeEach // Before test method, setup this...
    void setUp() {
        Artifact a1 = new Artifact();
        a1.setId("1250808601744904191");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dumbledore that resembles a cigarette lighter. It is used to remove or absorb (as well as return) the light from any light source to provide cover to the user.");
        a1.setImageUrl("imageUrl");

        Artifact a2 = new Artifact();
        a2.setId("1250808601744904192");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a2.setImageUrl("imageUrl");

        this.artifacts = new ArrayList<>();
        this.artifacts.add(a1);
        this.artifacts.add(a2);

    }

    @AfterEach // After calls, clean up...
    void tearDown() {
    }

    @Test // Test method
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define behavior of Mock object
        /* data": {
        "id": "1250808601744904192",
        "name": "Invisibility Cloak",
        "description": "An invisibility cloak is used to make the wearer invisible.",
        "imageUrl": "ImageUrl",
    */
        // Create fake data
        Artifact a = new Artifact();
        a.setId("1250808601744904192");
        a.setName("Invisibility Cloak");
        a.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a.setImageUrl("ImageUrl");

        Wizard w = new Wizard();

        w.setId(2);
        w.setName("Harry Potter");

        a.setOwner(w);

        //Defines the behavior of mock object
        given(artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(a)); // Before mocking, know what it returns, also wrap in optional option

        // When. Act on the target behavior. When steps should cover the method to be tested.

        Artifact returnedArtifact = artifactService.findById("1250808601744904192");

        // Then. (Assert) expected outcomes.

        assertThat(returnedArtifact.getId()).isEqualTo(a.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(a.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(a.getImageUrl());
        verify(artifactRepository, times(1)).findById("1250808601744904192"); // Verify that the calls exactly once
    }

    @Test
    void testFindByIdNotFound(){
        // Given
        given(artifactRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty()); // This is how we create an empty

        // When
        Throwable thrown = catchThrowable(() -> { // If artifactService throws an exception catch it for the then
                    Artifact returnedArtifact = artifactService.findById("1250808601744904192");
                }
                );

        // Then

        assertThat(thrown)
                .isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Could not find artifact with Id 1250808601744904192 :(");
        verify(artifactRepository, times(1)).findById("1250808601744904192"); // Verify that the calls exactly once

    }

    @Test
    void testFindAllSuccess(){
        // Given
        given(artifactRepository.findAll()).willReturn(this.artifacts);
        // When
        List<Artifact> actualArtifacts = artifactService.findAll();

        // Then
        assertThat(actualArtifacts.size()).isEqualTo(this.artifacts.size());
        verify(artifactRepository, times(1)).findAll();
    }

    @Test
    void testSaveSuccess(){
        // Given
        Artifact newArtifact = new Artifact();
        newArtifact.setName("Artifact 3");
        newArtifact.setDescription("Description");
        newArtifact.setImageUrl("ImageUrl...");

        given(idWorker.nextId()).willReturn(123456L); // This what the idWorker will 'generate' will give this number every time under the simulation
        given(artifactRepository.save(newArtifact)).willReturn(newArtifact); // New artifact comes in, should return that same artifact
        // When

        Artifact savedArtifact = artifactService.save(newArtifact);// invoke the method

        // Then
        assertThat(savedArtifact.getId()).isEqualTo("123456"); // We should get EXACTLY what we put in
        assertThat(savedArtifact.getName()).isEqualTo(newArtifact.getName());
        assertThat(savedArtifact.getDescription()).isEqualTo(newArtifact.getDescription());
        assertThat(savedArtifact.getImageUrl()).isEqualTo(newArtifact.getImageUrl());
        verify(artifactRepository, times(1)).save(newArtifact); // Verify it was called once

    }

}