package de.szut.bonuscalculator.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BonusServiceTest {

    @Test
    void shouldApplyMinimumRestriction() {
        // Given
        BonusService bonusService = new BonusService();
        double calculatedBonus = 5.0; // Unter dem Minimum von 10

        // When
        double adjustedBonus = bonusService.applyRestrictions(calculatedBonus);

        // Then
        assertThat(adjustedBonus).isEqualTo(10.0);
    }

    @Test
    void shouldApplyMaximumRestriction() {
        // Given
        BonusService bonusService = new BonusService();
        double calculatedBonus = 1500.0; // Über dem Maximum von 1000

        // When
        double adjustedBonus = bonusService.applyRestrictions(calculatedBonus);

        // Then
        assertThat(adjustedBonus).isEqualTo(1000.0);
    }

    @Test
    void shouldNotAdjustBonusWithinLimits() {
        // Given
        BonusService bonusService = new BonusService();
        double calculatedBonus = 500.0; // Innerhalb der Grenzen

        // When
        double adjustedBonus = bonusService.applyRestrictions(calculatedBonus);

        // Then
        assertThat(adjustedBonus).isEqualTo(500.0);
    }

}