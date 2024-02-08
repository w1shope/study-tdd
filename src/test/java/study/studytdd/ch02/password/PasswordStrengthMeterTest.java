package study.studytdd.ch02.password;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.studytdd.chap02.PasswordStrength;
import study.studytdd.chap02.PasswordStrengthMeter;

/**
 * TDD는 기능을 검증하는 테스트를 먼저 작성한다.
    - 통과하지 못하는 테스트는 통과할 만큼의 코드만 작성한다 => "통과시키기 위한 코드만" 최소한으로 작성한다
    - 테스트를 통과하면 refactoring 가능한지 판단한다 => 리팩터링 후 반드시 기존 코드가 테스트 통과가 되는지 확인한다.
 * 테스트 -> 코딩 -> 리팩토링 과정을 반복ㅎ면서 점진적으로 기능을 완성해 나가는 것, 이것이 TDD 흐름이다.
 */
public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    /**
     * @param password: 강도를 검증할 비밀번호
     * @param expected: 강도 기대값
     */
    private void assertStrength(String password, PasswordStrength expected) {
        PasswordStrength result = meter.meter(password);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("모든 규칙을 만족하면 강한 강도를 반환한다")
    void meetsAllCriteriaThenStrong() {
//        PasswordStrength result = meter.meter("ab12!@AB");
//        Assertions.assertEquals(PasswordStrength.STRONG, result);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8글자 미만인 것 빼고는 모두 만족하므로 보통 강도를 반환한다")
    void meetsOtherCriteriaExceptForLengthThenNormal() {
//        PasswordStrength result = meter.meter("ab12@A");
//        Assertions.assertEquals(PasswordStrength.NORMAL, result);
        assertStrength("ab12@A", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않는 것 빼고는 모두 만족하므로 보통 강도를 반환한다")
    void meetsOtherCriteriaExceptForNumberThenNormal() {
//        PasswordStrength result = meter.meter("ab!@ABqwer");
//        Assertions.assertEquals(PasswordStrength.NORMAL, result);
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호에 null 값이 들어오면 INVALID 강도를 반환한다")
    void inputNullThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않는 것 빼고는 모두 만족하므로 보통 강도를 반환한다")
    void meetsOtherCriteriaExceptForUpperCaseThenNormal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호가 8글자 이상인 조건만 만족하면 약함 강도를 반환한다")
    void meetsOnlyLengthCriteriaThenWeak() {
        assertStrength("abcdefgh", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자만 포함하는 조건을 만족하면 약함 강도를 반환한다")
    void meetsOnlyNumberCriteriaThenWeak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자를 포함하는 조건을 만족하면 약함 강도를 반환한다")
    void meetsOnlyUpperCaseCriteriaThenWeak() {
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건을 만족하지 않는 경우 약함 강도를 반환한다")
    void meetsNoCreteriaThenWeak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
