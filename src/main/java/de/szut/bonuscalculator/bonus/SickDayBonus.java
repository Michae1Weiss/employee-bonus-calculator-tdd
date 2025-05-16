package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SickDayBonus extends BonusDecorator {
    private static final double BONUS_TIER_1 = 10.0; // 0-5 Fehltage
    private static final double BONUS_TIER_2 = 5.0;  // 5-10 Fehltage
    private static final int TIER_1_MAX = 5;
    private static final int TIER_2_MAX = 10;

    public SickDayBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employee.getSickDays() > 265) {
            throw new IllegalArgumentException("sickDays cannot be > 265");
        } else if (employee.getSickDays() < 0) {
            throw new IllegalArgumentException("sickDays cannot be < 0");
        }

        double baseBonus = decoratedBonus.calculateBonus(employee);
        int sickDays = employee.getSickDays();

        if (sickDays <= TIER_1_MAX) {
            return baseBonus + BONUS_TIER_1;
        } else if (sickDays <= TIER_2_MAX) {
            return baseBonus + BONUS_TIER_2;
        }

        return baseBonus;
    }
}
