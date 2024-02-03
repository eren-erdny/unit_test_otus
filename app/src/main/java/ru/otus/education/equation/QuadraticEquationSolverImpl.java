package ru.otus.education.equation;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.isNaN;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class QuadraticEquationSolverImpl implements QuadraticEquationSolver {
    @Override
    public double[] solve(double a, double b, double c) {
        if (isNaN(a) || isNaN(b) || isNaN(c)) {
            throw new IllegalArgumentException("Coefficients must not be NaN");
        }

        if (abs(a) >= POSITIVE_INFINITY - eps ||
                abs(b) >= POSITIVE_INFINITY - eps ||
                abs(c) >= POSITIVE_INFINITY - eps) {
            throw new IllegalArgumentException("Coefficients must not be an infinite values");
        }

        if (equalToZero(a)) {
            throw new IllegalArgumentException("A must not be 0");
        }

        var root1 = eps;
        var root2 = -eps;

        var D = b * b - 4 * a * c;

        if (lessThanZero(D)) {
            return new double[0];
        }

        if (greaterThanZero(D)) {
            root1 = (-b + sqrt(D)) / 2 * a;
            root2 = (-b - sqrt(D)) / 2 * a;
            return new double[] {root1, root2};
        }

        if (equalToZero(D)) {
            root1 = -b / 2 * a;
            return new double[] {root1};
        }

       throw new RuntimeException("Rest of the logic is not implemented");
    }

    private static boolean equalToZero(double value) {
        return value - eps <= 0 && value + eps >= 0;
    }

    private static boolean greaterThanZero(double value) {
        return value - eps > 0;
    }

    private static boolean lessThanZero(double value) {
        return value + eps < 0;
    }
}
