package org.mps.mutation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mps.EvolutionaryAlgorithmException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
public class SwapMutationTest {


    @ParameterizedTest
    @MethodSource("provideTestIndividuals")
    @DisplayName("SwapMutation: mutated individual is not null, has the same length and is different from the test individual")
    public void SwapMutation_CorrectIndividual_ReturnsSuccess(int[] testIndividual) throws EvolutionaryAlgorithmException {
        // 1. Create a new instance of SwapMutation
        SwapMutation swapMutation = new SwapMutation();

        // 3. Call the mutate method
        int[] mutatedIndividual = swapMutation.mutate(testIndividual);

        // 4. Assert that the returned individual is not null and has the same length
        assertNotNull(mutatedIndividual);
        assertEquals(testIndividual.length, mutatedIndividual.length);

        // 5. Assert that the returned individual is not the same as the test individual
        assertNotEquals(testIndividual, mutatedIndividual);
    }

    private static Stream<Arguments> provideTestIndividuals() {
        return Stream.of(
                Arguments.of((Object) new int[]{1, 2, 3, 4, 5}),
                Arguments.of((Object) new int[]{6, 7, 8, 9, 10}),
                Arguments.of((Object) new int[]{11, 12, 13, 14, 15}),
                Arguments.of((Object) new int[]{16, 17, 18, 19, 20}),
                Arguments.of((Object) new int[]{21, 22, 23, 24, 25})
        );
    }

    @Test
    @DisplayName("SwapMutation: individual parameter is null throws EvolutionaryAlgorithmException")
    public void SwapMutation_NullIndividual_ThrowsEvolutionaryAlgorithmException() {
        // 1. Create a new instance of SwapMutation
        SwapMutation swapMutation = new SwapMutation();

        // 2. Call the mutate method with a null individual
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(null));
    }

    @Test
    @DisplayName("SwapMutation: individual parameter is empty (length 0) throws EvolutionaryAlgorithmException")
    public void SwapMutation_EmptyIndividual_ThrowsEvolutionaryAlgorithmException() {
        // 1. Create a new instance of SwapMutation
        SwapMutation swapMutation = new SwapMutation();

        // 2. Call the mutate method with an empty individual
        assertThrows(EvolutionaryAlgorithmException.class, () -> swapMutation.mutate(new int[]{}));
    }
}