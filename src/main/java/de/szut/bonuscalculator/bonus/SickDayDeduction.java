package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SickDayDeduction extends BonusDecorator {
    protected SickDayDeduction(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
