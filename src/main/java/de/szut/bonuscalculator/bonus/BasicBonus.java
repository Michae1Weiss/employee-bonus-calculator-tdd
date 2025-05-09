package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;

public class BasicBonus implements Bonus {
    @Override
    public double calculateBonus(Employee employee) {
        return 0;
    }
}
