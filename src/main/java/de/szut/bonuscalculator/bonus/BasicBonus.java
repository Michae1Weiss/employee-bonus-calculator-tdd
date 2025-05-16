package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class BasicBonus implements Bonus {
    private static final double BASIC_BONUS_AMOUNT = 500.0;

    @Override
    public double calculateBonus(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }
        return BASIC_BONUS_AMOUNT;
    }
}
