/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.send;

import java.text.Normalizer;

/**
 *
 * @author ADDMIN
 */
// hàm check validate nhập input tìm kiếm
public class TextUtils {

    // Dùng khi người dùng gõ "a", "n", không dấu → cho phép tìm fuzzy (ân, á, ă)
    public static String normalizeForFuzzySearch(String input) {
        if (input == null) {
            return "";
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "")
                .replaceAll("\\s+", "")
                .toLowerCase();
    }

    // Dùng khi người dùng gõ chính xác: "ân", "Nguyễn Ân"
    public static String normalizeStrict(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\s+", "")
                .toLowerCase();
    }

}
