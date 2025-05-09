package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SickDayDeduction extends BonusDecorator {
    private static final double DEDUCTION_TIER_1 = 200.0; // 10-20 Fehltage
    private static final double DEDUCTION_TIER_2 = 400.0; // über 20 Fehltage
    private static final int TIER_1_MIN = 10;
    private static final int TIER_2_MIN = 20;

    public SickDayDeduction(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        double baseBonus = decoratedBonus.calculateBonus(employee);
        int sickDays = employee.getSickDays();

        if (sickDays > TIER_2_MIN) {
            return baseBonus - DEDUCTION_TIER_2;
        } else if (sickDays > TIER_1_MIN) {
            return baseBonus - DEDUCTION_TIER_1;
        }

        return baseBonus;
    }
}
