import Roll.RollLineUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RollLineUtilsTest {
    @Test
    void getAccessible() {
        var expected = TestInputProvider.getExpected();
        var input = TestInputProvider.getInput();
        var result = RollLineUtils.getAccessible(input);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @Test
    void removeAccessible() {
        var input = RollLineUtils.getAccessible(TestInputProvider.getInput());
        var result = RollLineUtils.removeAccessible(input);
        assertThat(result).isEqualTo(13);
        assertThat(input).containsExactlyElementsOf(TestInputProvider.getExpectedWithRemovedAccessibles());
    }
}
