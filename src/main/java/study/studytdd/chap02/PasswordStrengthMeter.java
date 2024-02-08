package study.studytdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        // 문자열이 null이면 INVALID 강도를 반환한다.
        if(s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        /**
         * refactor: 메서드로 분리
        boolean lengthCriteriaEnough = s.length() >= 8;
        boolean containsNum = meetsContainingNumberCriteria(s);
        boolean containsUpperCase = meetsContainingUpperCaseCriteria(s);

        int meetCount = 0;

        // 문자열의 길이가 8글자 이상인 조건만 만족하면 약함 강도를 반환한다.
        if(lengthCriteriaEnough) {
            meetCount++;
        }
        if(containsNum) {
            meetCount++;
        }
        if(containsUpperCase) {
            meetCount++;
        }
        */

        /**
         * refactor: meetCount 변수 사용
         // 문자열에 숫자가 포함되는 조건만을 만족하면 약함 강도를 반환한다.
        if(lengthCriteriaEnough && !containsNum && !containsUpperCase) {
            return PasswordStrength.WEAK;
        }
         // 문자열에 대문자가 포함되는 조건만을 만족하면 약함 강도를 반환한다.
        if(!lengthCriteriaEnough && containsNum && !containsUpperCase) {
            return PasswordStrength.WEAK;
        }
         // 문자열에 대문자가 포함되는 조건만을 만족하면 약함 강도를 반환한다.
        if(!lengthCriteriaEnough && !containsNum && containsUpperCase) {
            return PasswordStrength.WEAK;
        }
         */

        /**
         * refactor: meetCount 변수 사용
        // 문자열의 길이가 8 미만이면 보통 강도를 반환한다.
        if (!lengthCriteriaEnough) {
            return PasswordStrength.NORMAL;
        }
        // 문자열에 숫자가 포함되지 않은 경우 보통 강도를 반환한다.
        if(!containsNum) {
            return PasswordStrength.NORMAL;
        }
        // 문자열에 대문자가 포함되지 않은 경우 보통 강도를 반환한다.
        if(!containsUpperCase) {
            return PasswordStrength.NORMAL;
        }
         */

        int meetCount = getMeetCriteriaCounts(s);

        if(meetCount <= 1) {
            return PasswordStrength.WEAK;
        }
        if(meetCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private static int getMeetCriteriaCounts(String s) {
        int meetCount = 0;
        if(s.length() >= 8) {
            meetCount++;
        }
        if(meetsContainingNumberCriteria(s)) {
            meetCount++;
        }
        if(meetsContainingUpperCaseCriteria(s)) {
            meetCount++;
        }
        return meetCount;
    }

    private static boolean meetsContainingUpperCaseCriteria(String s) {
        boolean containsUpperCase = false;
        for(char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) {
                containsUpperCase = true;
                break;
            }
        }
        return containsUpperCase;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        for (char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') {
                return true;
            }
        }
        return false;
    }
}
