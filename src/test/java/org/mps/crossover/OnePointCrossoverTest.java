package org.mps.crossover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class OnePointCrossoverTest {

    static CrossoverOperator crossoverOperator;

    @BeforeAll
    public static void setUp() {
     crossoverOperator = new OnePointCrossover();
    }

    @Test
    @DisplayName("Test de prueba para el método crossover, cuando los padres son válidos, debe retornar los hijos")
    public void crossover_WhenValidParents_ReturnsOffspring() {

        // Arrange
        int[] parent1 = {1, 2, 3, 4, 5};
        int[] parent2 = {6, 7, 8, 9, 10};

        // Act
        int[][] offspring = null;
        try {
            offspring = crossoverOperator.crossover(parent1, parent2);
        } catch (EvolutionaryAlgorithmException e) {
            assert false;
        }

        // Assert
        assertNotNull(offspring);
        assertEquals(2, offspring.length);
        assertEquals(5, offspring[0].length);
        assertEquals(5, offspring[1].length);
    }

    @Test
    @DisplayName("Test de prueba para el método crossover, cuando al menos uno de los padres es nulo, debe lanzar una excepción")
    public void crossover_WhenNullParent_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] parent1 = {1, 2, 3, 4, 5};
        int[] parent2 = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {crossoverOperator.crossover(parent1, parent2);});
        assertThrows(EvolutionaryAlgorithmException.class, () -> {crossoverOperator.crossover(parent2, parent1);});
        assertThrows(EvolutionaryAlgorithmException.class, () -> {crossoverOperator.crossover(null, null);});
    }

    @Test
    @DisplayName("Test de prueba para el método crossover, cuando los padres tienen diferente longitud, debe lanzar una excepción")
    public void crossover_WhenDifferentLengthParents_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] parent1 = {1, 2, 3, 4, 5};
        int[] parent2 = {6, 7, 8, 9, 10, 11};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {crossoverOperator.crossover(parent1, parent2);});
    }

    @Test
    @DisplayName("Test de prueba para el método crossover, cuando un padre tiene longitud 0, debe lanzar una excepción")
    public void crossover_WhenEmptyParent_ThrowsEvolutionaryAlgorithmException() {

        // Arrange
        int[] parent1 = {};
        int[] parent2 = {1, 2, 3, 4, 5};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {crossoverOperator.crossover(parent1, parent2);});

        //En el método solo se valida que la longitud del primer padre sea mayor a 0, pero como ambos padres deben tener la misma longitud,
        //si uno de los padres tiene longitud mayor a 0, el otro padre también la tendrá.
    }

}
