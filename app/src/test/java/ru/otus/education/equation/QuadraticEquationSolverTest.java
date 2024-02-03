package ru.otus.education.equation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.education.equation.QuadraticEquationSolver.eps;

class QuadraticEquationSolverTest {

    private static final QuadraticEquationSolver solver = new QuadraticEquationSolverImpl();
    @Test
    void whenCase1_thenNoRoots() {
        // Given
        double a = 1, b = 0, c = 1;
        var expectedSize = 0;
        // When
        var result = solver.solve(a, b, c);
        // Then
        Assertions.assertEquals(expectedSize, result.length);
    }

    @Test
    void whenCase2_thenTwoRootsReturned() {
        // Given
        double a = 1, b = 0, c = -1;
        var expectedRoot1 = 1;
        var expectedRoot2 = -1;
        // When
        var result = solver.solve(a, b, c);
        //Then
        Assertions.assertAll(
                () -> assertEquals(2, result.length),
                () -> assertTrue(Math.abs(result[0] - expectedRoot1) < eps),
                () -> assertTrue(Math.abs(result[1] - expectedRoot2) < eps)
        );
    }

    @Test
    void whenCase3_thenTwoSameRootsReturned() {
        // Given
        double a = 1, b = 1, c = (1 + 10e-10 - eps)/4;
        var expectedRoot = -0.5;
        // When
        var result = solver.solve(a, b, c);
        // Then
        Assertions.assertAll(
                () -> assertEquals(1, result.length),
                () -> assertTrue(Math.abs(result[0] - expectedRoot) < eps)
        );
    }

    @Test
    void whenCase4_AisZero_thenThrowException() {
        // Given
        double a = 0, b = 1, c = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(a, b, c));
    }

    @Test
    void whenCase5_givenAInfinity_thenThrowException() {
        // Given
        double b = 1, c = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(POSITIVE_INFINITY, b, c));
    }

    @Test
    void whenCase5_givenBInfinity_thenThrowException() {
        // Given
        double a = 1, c = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(a, POSITIVE_INFINITY, c));
    }

    @Test
    void whenCase5_givenCInfinity_thenThrowException() {
        // Given
        double a = 1, b = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(a, b, POSITIVE_INFINITY));
    }

    @Test
    void whenCase6_givenANaN_thenThrowException() {
        // Given
        double b = 1, c = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(NaN, b, c));
    }

    @Test
    void whenCase6_givenBNaN_thenThrowException() {
        // Given
        double a = 1, c = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(a, NaN, c));
    }

    @Test
    void whenCase6_givenCNaN_thenThrowException() {
        // Given
        double a = 1, b = 1;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(a, b, NaN));
    }
}