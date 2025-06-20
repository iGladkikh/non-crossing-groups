package com.igladkikh.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilTest {

    @Test
    void validLines() {
        String line1 = """
                ;"3563.1";
                """;

        String line2 = """
                "79878495188";"79905944369";"79321107389";"79578498664";"79424552478";"79614705719";"79660605765"
                """;

        String line3 = """
                ;;
                """;

        assertTrue(isValid(line1));
        assertTrue(isValid(line2));
        assertTrue(isValid(line3));
    }

    @Test
    void invalidLines() {
        String line1 = """
                "8383"200000741652251"
                """;

        String line2 = """
                "79855053897"83100000580443402";"200000133000191"
                """;

        assertFalse(isValid(line1));
        assertFalse(isValid(line2));
    }

    private boolean isValid(String line) {
        return Arrays.stream(getFragments(line)).allMatch(FragmentUtil::isCorrect);
    }

    private String[] getFragments(String line) {
        return correctLine(line).split(";");
    }

    private String correctLine(String line) {
        return line.replace("\n", "").replace("\r", "");
    }
}