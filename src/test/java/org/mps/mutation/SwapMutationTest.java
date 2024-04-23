package org.mps.mutation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mps.EvolutionaryAlgorithmException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
public class SwapMutationTest {

    private static SwapMutation swapMutation;

    @BeforeAll
    public static void setUp() {
        swapMutation = new SwapMutation();
    }


    @Test
    @DisplayName("mutate: cuando el individuo pasado es valido, debe retornar un individuo mutado, de la misma longitud y diferente al original")
    public void SwapMutation_WhenCorrectIndividual_ReturnsMutated() throws EvolutionaryAlgorithmException {

        // Arrange
        int[] testIndividual = {1, 2, 3, 4, 5};

        // Act
        int[] mutatedIndividual = swapMutation.mutate(testIndividual);

        // Asserts
        assertNotNull(mutatedIndividual);
        assertEquals(testIndividual.length, mutatedIndividual.length);
        assertNotEquals(testIndividual, mutatedIndividual);
    }

    @Test
    @DisplayName("mutate: cuando el individuo pasado es nulo, debe lanzar una excepción")
    public void mutate_WhenNullIndividual_ThrowsEvolutionaryAlgorithmException() {
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(null));
    }

    @Test
    @DisplayName("mutate: cuando el individuo pasado es vacío, debe lanzar una excepción")
    public void SwapMutation_EmptyIndividual_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] testIndividual = {};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(testIndividual));
    }
}