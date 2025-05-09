package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class ProjectCompletionBonus extends BonusDecorator {
    private static final double BONUS_PER_PROJECT = 10.0;

    public ProjectCompletionBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculateBonus(Employee employee) {
        double baseBonus = decoratedBonus.calculateBonus(employee);
        double projectCompletionBonus = employee.getCompletedProjects() * BONUS_PER_PROJECT;

        return baseBonus + projectCompletionBonus;
    }
}
