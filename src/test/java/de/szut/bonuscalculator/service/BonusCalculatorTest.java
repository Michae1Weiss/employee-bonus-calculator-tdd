package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BonusCalculatorTest {

    @Mock
    private BonusService bonusService;

    @InjectMocks
    private BonusCalculator bonusCalculator;

    @Test
    void shouldCalculateTotalBonusRespectingMinimumLimit() {
        // Given
        when(bonusService.applyRestrictions(anyDouble())).thenAnswer(invocation -> {
            double bonus = invocation.getArgument(0);
            return Math.max(10.0, Math.min(1000.0, bonus));
        });

        Employee employee = Employee.builder()
                .name("Max Mustermann")
                .yearsOfService(2)
                .performanceRating(2) // niedriger Performance-Faktor (0.5)
                .completedProjects(1)
                .sickDays(5)
                .isTeamLeader(false)
                .build();

        // When
        double bonus = bonusCalculator.calculateTotalBonus(employee);

        // Then
        // Grundbonus: 500
        // Seniority: 2 * 10 = 20
        // Project: 1 * 10 = 10
        // TeamLeader: 0
        // SickDayBonus: 10 (0-5 Tage)
        // SickDayDeduction: 0
        // Zwischensumme: 540
        // PerformanceMultiplier: 540 * 0.5 = 270

        assertThat(bonus).isEqualTo(270.0); // Über Minimum, daher kein Einfluss
    }

    @Test
    void shouldCalculateTotalBonusRespectingMaximumLimit() {
        // Given
        when(bonusService.applyRestrictions(anyDouble())).thenAnswer(invocation -> {
            double bonus = invocation.getArgument(0);
            return Math.max(10.0, Math.min(1000.0, bonus));
        });

        Employee employee = Employee.builder()
                .name("Anna Schmidt")
                .yearsOfService(15)
                .performanceRating(9) // hoher Performance-Faktor (1.1)
                .completedProjects(20)
                .sickDays(3)
                .isTeamLeader(true)
                .build();

        // When
        double bonus = bonusCalculator.calculateTotalBonus(employee);

        // Then
        // Grundbonus: 500
        // Seniority: (10 * 10) + (5 * 20) = 100 + 100 = 200
        // Project: 20 * 10 = 200
        // TeamLeader: 20 = 20
        // SickDayBonus: 10 (0-5 Tage)
        // SickDayDeduction: 0
        // Zwischensumme: 930
        // PerformanceMultiplier: 930 * 1.1 = 1023
        // Über Maximum von 1000, also sollte 1000 zurückgegeben werden

        assertThat(bonus).isEqualTo(1000.0);
    }
}
