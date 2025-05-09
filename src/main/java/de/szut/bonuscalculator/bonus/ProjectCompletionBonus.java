package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class ProjectCompletionBonus extends BonusDecorator {
    protected ProjectCompletionBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
