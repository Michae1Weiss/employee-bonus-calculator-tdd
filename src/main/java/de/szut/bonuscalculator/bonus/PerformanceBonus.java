package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class PerformanceBonus extends BonusDecorator {
    protected PerformanceBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
