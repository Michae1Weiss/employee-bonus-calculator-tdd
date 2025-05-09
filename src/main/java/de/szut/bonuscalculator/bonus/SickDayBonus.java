package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SickDayBonus extends BonusDecorator {
    protected SickDayBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
