package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class PerformanceBonus extends BonusDecorator {
    private static final double LOW_MULTIPLIER = 0.5;   // 0-3 Rating
    private static final double MID_MULTIPLIER = 1.0;   // 4-7 Rating
    private static final double HIGH_MULTIPLIER = 1.1;  // 8-10 Rating
    private static final int LOW_MAX = 3;
    private static final int MID_MAX = 7;

    protected PerformanceBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        double baseBonus = decoratedBonus.calculateBonus(employee);
        int performanceRating = employee.getPerformanceRating();

        if (performanceRating <= LOW_MAX) {
            return baseBonus * LOW_MULTIPLIER;
        } else if (performanceRating <= MID_MAX) {
            return baseBonus * MID_MULTIPLIER;
        } else {
            return baseBonus * HIGH_MULTIPLIER;
        }
    }
}
