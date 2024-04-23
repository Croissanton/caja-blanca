package org.mps;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

import static org.junit.jupiter.api.Assertions.*;

public class EvolutionaryAlgorithmTest {

    private static SelectionOperator selectionOperator;
    private static MutationOperator mutationOperator;
    private static CrossoverOperator crossoverOperator;

    private static EvolutionaryAlgorithm evolutionaryAlgorithm;

    @BeforeAll
    public static void setUp() throws EvolutionaryAlgorithmException {
        selectionOperator = new TournamentSelection(4);
        mutationOperator = new SwapMutation();
        crossoverOperator = new OnePointCrossover();

        evolutionaryAlgorithm = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
    }

    @Test
    @DisplayName("EvolutionaryAlgorithm initialization: si todos los operadores son validos, deberia inicializar correctamente")
    public void initialize_whenOperatorsAreValid_ReturnsSuccess() throws EvolutionaryAlgorithmException {
        //Act & Assert
        assertDoesNotThrow(() -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("EvolutionaryAlgorithm initialization: si alguno de los operadores es nulo, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void initialize_whenThereIsNullOperator_ReturnsException() throws EvolutionaryAlgorithmException {
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(null, mutationOperator, crossoverOperator));
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, null, crossoverOperator));
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, null));
    }

    @Test
    @DisplayName("optimize: si la poblacion es valida, deberia retornar la poblacion optimizada")
    public void optimize_whenPopulationIsValid_ReturnsOptimizedPopulation() throws EvolutionaryAlgorithmException {
        //Arrange
        int[][] population = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};

        //Act & Assert
        assertDoesNotThrow(() -> evolutionaryAlgorithm.optimize(population));
    }

    @Test
    @DisplayName("optimize: si la poblacion es nula, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void optimize_whenPopulationIsNull_ReturnsException() throws EvolutionaryAlgorithmException {
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> evolutionaryAlgorithm.optimize(null));
    }

    @Test
    @DisplayName("optimize: si la poblacion esta vacia, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void optimize_whenPopulationIsEmpty_ReturnsException() throws EvolutionaryAlgorithmException {
        //Arrange
        int[][] population = new int[][]{};

        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> evolutionaryAlgorithm.optimize(population));
    }

    @Test
    @DisplayName("optimize: si la poblacion contiene individuos vacios, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void optimize_whenPopulationContainsEmptyIndividuals_ReturnsException() throws EvolutionaryAlgorithmException {
        //Arrange
        int[][] population = new int[][]{{}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};

        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> evolutionaryAlgorithm.optimize(population));
    }

    @Test
    @DisplayName("optimize: si la poblacion contiene individuos nulos, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void optimize_whenPopulationContainsNullIndividuals_ReturnsException() throws EvolutionaryAlgorithmException {
        //Arrange
        int[][] population = new int[][]{null, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};

        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> evolutionaryAlgorithm.optimize(population));
    }

    @Test
    @DisplayName("optimize: si la poblacion no es par, deberia lanzar una excepcion EvolutionaryAlgorithmException")
    public void optimize_whenPopulationIsNotEven_ReturnsException() throws EvolutionaryAlgorithmException {
        //Arrange
        int[][] population = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> evolutionaryAlgorithm.optimize(population));
    }

    @Test
    @DisplayName("setters: si los operadores son validos, deberia retornar exito")
    public void setOperators_whenOperatorsAreValid_ReturnsSuccess() throws EvolutionaryAlgorithmException {
        //Arrange
        SelectionOperator newSelectionOperator = new TournamentSelection(2);
        MutationOperator newMutationOperator = new SwapMutation();
        CrossoverOperator newCrossoverOperator = new OnePointCrossover();

        //Assert para comprobar los operadores iniciales
        assertEquals(selectionOperator, evolutionaryAlgorithm.getSelectionOperator());
        assertEquals(mutationOperator, evolutionaryAlgorithm.getMutationOperator());
        assertEquals(crossoverOperator, evolutionaryAlgorithm.getCrossoverOperator());

        //Act
        evolutionaryAlgorithm.setSelectionOperator(newSelectionOperator);
        evolutionaryAlgorithm.setMutationOperator(newMutationOperator);
        evolutionaryAlgorithm.setCrossoverOperator(newCrossoverOperator);

        //Assert para comprobar los operadores actualizados
        assertEquals(newSelectionOperator, evolutionaryAlgorithm.getSelectionOperator());
        assertEquals(newMutationOperator, evolutionaryAlgorithm.getMutationOperator());
        assertEquals(newCrossoverOperator, evolutionaryAlgorithm.getCrossoverOperator());
    }


}
