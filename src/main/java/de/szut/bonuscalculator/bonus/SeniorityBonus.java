package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SeniorityBonus extends BonusDecorator {
    private static final int BASE_RATE_PER_YEAR = 10;
    private static final int YEARS_THRESHOLD = 10;
    private static final int ADDITIONAL_RATE = 10;

    protected SeniorityBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        double baseBonus = decoratedBonus.calculateBonus(employee);
        return baseBonus + calculateSeniorityBonus(employee.getYearsOfService());
    }

    private double calculateSeniorityBonus(int years) {
        if (years <= YEARS_THRESHOLD) {
            return years * BASE_RATE_PER_YEAR;
        } else {
            int bonusRate = BASE_RATE_PER_YEAR + ADDITIONAL_RATE;
            return (YEARS_THRESHOLD * BASE_RATE_PER_YEAR) +
                    ((years - YEARS_THRESHOLD) * bonusRate);
        }
    }
}
