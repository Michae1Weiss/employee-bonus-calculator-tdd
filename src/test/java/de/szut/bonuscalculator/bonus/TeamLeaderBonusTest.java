package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TeamLeaderBonusTest {
    @Test
    void shouldAddBonusForTeamLeader() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new TeamLeaderBonus(wrappedComponent);

        // Fall 1: Teamleiter mit 5 Jahren
        Employee teamLeader = Employee.builder()
                .isTeamLeader(true)
                .yearsOfService(5)
                .build();

        // Fall 2: Kein Teamleiter
        Employee nonTeamLeader = Employee.builder()
                .isTeamLeader(false)
                .yearsOfService(5)
                .build();

        // When
        double bonusTeamLeader = decorator.calculateBonus(teamLeader);
        double bonusNonTeamLeader = decorator.calculateBonus(nonTeamLeader);

        // Then
        assertThat(bonusTeamLeader).isEqualTo(600.0); // 500 + (5 * 20)
        assertThat(bonusNonTeamLeader).isEqualTo(500.0); // unverändert

        verify(wrappedComponent, times(2)).calculateBonus(any(Employee.class));
    }
}
