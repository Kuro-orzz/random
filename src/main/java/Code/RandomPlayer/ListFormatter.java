package Code.RandomPlayer;

import java.text.Normalizer;
import java.util.List;

public class ListFormatter {
    public String formatList(List<String> data) {
        StringBuilder list = new StringBuilder();
        int columnWidth = 30;

        for (int i = 0; i < data.size(); i++) {
            String name = data.get(i);
            int displayLength = getDisplayLength(name);
            list.append(name);
            for (int j = 0; j < (columnWidth - displayLength); j++) {
                list.append(" ");
            }
            if ((i + 1) % 3 == 0) {
                list.append("\n");
            }
        }

        return list.toString();
    }

    public static int getDisplayLength(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);
        int length = 0;

        for (char c : normalized.toCharArray()) {
            if (c <= 0x007F) {
                length += 1;
            } else {
                length += 2;
            }
        }

        return length;
    }
}
