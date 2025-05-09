package de.szut.bonuscalculator.service;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(bonusService.applyRestrictions()).thenReturn(100.0);
        when(bonusService.getMaximumBonus()).thenReturn(5000.0);

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
        // Aber unter Minimum von 100, also sollte 100 zurückgegeben werden

        assertThat(bonus).isEqualTo(270.0); // Über Minimum, daher kein Einfluss
    }

    @Test
    void shouldCalculateTotalBonusRespectingMaximumLimit() {
        // Given
        when(bonusService.getMinimumBonus()).thenReturn(100.0);
        when(bonusService.getMaximumBonus()).thenReturn(1000.0);

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
        // TeamLeader: 15 * 20 = 300
        // SickDayBonus: 10 (0-5 Tage)
        // SickDayDeduction: 0
        // Zwischensumme: 1210
        // PerformanceMultiplier: 1210 * 1.1 = 1331
        // Über Maximum von 1000, also sollte 1000 zurückgegeben werden

        assertThat(bonus).isEqualTo(1000.0);
    }
}
