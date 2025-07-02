package dal;

import java.text.Normalizer;

public class ValidateDAO {

    /**
     * Normalize dạng fuzzy mạnh nhất: - Bỏ dấu - Bỏ khoảng trắng - Lowercase →
     * Dùng khi muốn tìm mờ (ví dụ "n g o c" → "ngoc")
     */
    public static String normalizeFuzzyMax(String input) {
        if (input == null) {
            return "";
        }
        // Bỏ dấu
        String noAccent = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Bước 2: Xử lý ký tự đặc biệt không bị NFD tác động (như "Đ")
        noAccent = noAccent.replaceAll("Đ", "D").replaceAll("đ", "d");

        // Bỏ khoảng trắng + lowercase
        return noAccent.replaceAll("\\s+", "").toLowerCase();
    }

    /**
     * Normalize dạng strict: - Giữ dấu - Bỏ khoảng trắng - Lowercase → Dùng để
     * so khớp chính xác khi người dùng gõ đúng có dấu.
     */
    public static String normalizeStrict(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\s+", "").toLowerCase();
    }

    /**
     * Kiểm tra xem chuỗi có dấu tiếng Việt không
     */
    public static boolean hasVietnameseAccent(String input) {
        if (input == null) {
            return false;
        }
        String decomposed = Normalizer.normalize(input, Normalizer.Form.NFD);
        return decomposed.matches(".*\\p{M}.*");
    }

    /**
     * Hàm fuzzy match tên (họ tên) với từ khóa nhập vào - Nếu từ khóa có dấu:
     * so khớp strict - Nếu từ khóa không dấu: so khớp fuzzy max - Có thể nhập
     * nhiều từ: ví dụ "n g o c d u c"
     */
    public static boolean fuzzyMatchAllWords(String fullName, String rawKeyword) {
        if (rawKeyword == null || rawKeyword.trim().isEmpty()) {
            return true;
        }

        String fullNameFuzzy = normalizeFuzzyMax(fullName);
        String fullNameStrict = normalizeStrict(fullName);

        String[] keywords = rawKeyword.trim().split("\\s+");

        for (String word : keywords) {
            if (word.isEmpty()) {
                continue;
            }

            boolean hasAccent = hasVietnameseAccent(word);

            String keywordToCheck = hasAccent
                    ? normalizeStrict(word)
                    : normalizeFuzzyMax(word);

            boolean matched = hasAccent
                    ? fullNameStrict.contains(keywordToCheck)
                    : fullNameFuzzy.contains(keywordToCheck);

            if (!matched) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String keyword = "d u c";

        String[] testNames = {
            "Đức",
            "Dục",
            "Đúc",
            "Dương",
            "Nguyễn Văn Đức",
            "Ngọc Trâm",
            "Phát Tài",
            "Lê Văn Duy",
            "Len", // cho test "l e n"
            "Lên",
            "Lế"
        };

        System.out.println("Từ khóa: \"" + keyword + "\"");
        for (String name : testNames) {
            boolean matched = fuzzyMatchAllWords(name, keyword);
            System.out.printf(" - %-20s => %s%n", name, matched ? "✅ MATCH" : "❌ KHÔNG");
        }
    }

}
