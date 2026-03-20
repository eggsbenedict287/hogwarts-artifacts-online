package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    ArtifactService artifactService; // Class under tests

    @BeforeEach // Before test method, setup this...
    void setUp() {
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
}