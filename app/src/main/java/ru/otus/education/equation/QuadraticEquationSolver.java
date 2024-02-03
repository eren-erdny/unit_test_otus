package ru.otus.education.equation;

public interface QuadraticEquationSolver {
    double eps = 10e-6;
    double[] solve(double a, double b, double c);
}
