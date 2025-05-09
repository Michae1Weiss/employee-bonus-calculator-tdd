package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public abstract class BonusDecorator implements Bonus {
    protected final Bonus decoratedBonus;

    protected BonusDecorator(Bonus decoratedBonus) {
        this.decoratedBonus = decoratedBonus;
    }

    @Override
    public double calculateBonus(Employee employee) {
        return decoratedBonus.calculateBonus(employee);
    }
}
