package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class SeniorityBonus extends BonusDecorator {
    protected SeniorityBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
