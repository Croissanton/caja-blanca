//Cristian Ruiz Martín y Mikolaj Zabski

package org.mps.selection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mps.EvolutionaryAlgorithmException;

import java.lang.annotation.Native;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentSelectionTest {

    static SelectionOperator selectionOperator;
    static int[] population;

    @BeforeAll
    public static void setUp() throws EvolutionaryAlgorithmException {
        // Arrange
        population = new int[5];
        population = new int[]{1, 2, 3, 4, 5};
        selectionOperator = new TournamentSelection(population.length);
    }



    @Test
    @DisplayName("select: when the population is valid, it should return nonempty and nonnull array of integers")
    public void select_WhenValidPopulation_ReturnsNotNullNotEmpty(){

            // Act
        int[] selectedPopulation = null;
        try {
            selectedPopulation = selectionOperator.select(population);

        } catch (EvolutionaryAlgorithmException e) {
            assert false;
        }

            // Assert
            assertNotNull(selectedPopulation);
            assertNotEquals(0, selectedPopulation.length);
    }

    @Test
    @DisplayName("select: when the population is null, it should throw an exception")
    public void select_WhenNullPopulation_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] population = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {selectionOperator.select(population);});
    }

    @Test
    @DisplayName("select: when the population is empty, it should throw an exception")
    public void select_WhenEmptyPopulation_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] population = new int[0];

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {selectionOperator.select(population);});
    }

    @Test
    @DisplayName("TournamentSelection: when the population size is less than 1 it should throw an exception")
    public void TournamentSelection_WhenPopulationSizeLessThanOne_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int populationSize = 0;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {new TournamentSelection(populationSize);});
    }

    @Test
    @DisplayName("select: when the population size is different from tournament size, it should throw an exception")
    public void select_WhenPopulationSizeDifferentFromTournamentSize_ThrowsEvolutionaryAlgorithmException() throws EvolutionaryAlgorithmException {

        // Arrange
        int[] population = new int[5];
        int tournamentSize = 3;
        TournamentSelection selectionOperator2 = new TournamentSelection(tournamentSize);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {selectionOperator2.select(population);});
    }
}