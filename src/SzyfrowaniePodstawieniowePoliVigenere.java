import java.util.HashMap;
import java.util.Map;

public class SzyfrowaniePodstawieniowePoliVigenere {

    private final Map<Character, Integer> letterIndexMap = new HashMap<>();
    private final char[][] tabVinegre;

    public SzyfrowaniePodstawieniowePoliVigenere() {
        tabVinegre = setupVarTab();
        generateLetterIndexMap();
        printTab(tabVinegre);
    }

    public String encrypt(String text, String key) {
        String textWithoutSpaces = text.replace(" ", "q");
        StringBuilder encryptedText = new StringBuilder();
        String adjustedKey = textWithoutSpaces.length() != key.length() ? adjustKey(textWithoutSpaces, key) : key;
        if (textWithoutSpaces.length() != adjustedKey.length())
            throw new RuntimeException("Adjust key to text size error");

        for (int i = 0; i < textWithoutSpaces.length(); i++) {
            int textIndex = letterIndexMap.get(textWithoutSpaces.charAt(i));
            int keyIndex = letterIndexMap.get(adjustedKey.charAt(i));
            encryptedText.append(tabVinegre[textIndex][keyIndex]);
        }

        System.out.println(encryptedText);
        return encryptedText.toString();
    }

    public String decrypt(String encryptedtext, String key) {
        StringBuilder decodedText = new StringBuilder();
        String adjustedKey = encryptedtext.length() != key.length() ? adjustKey(encryptedtext, key) : key;
        for (int i = 0; i < encryptedtext.length(); i++) {
            int keyIndex = letterIndexMap.get(adjustedKey.charAt(i));
            char el;
            for (char[] chars : tabVinegre) {
                if (chars[keyIndex] == encryptedtext.charAt(i)) {
                    el = chars[0];
                    decodedText.append(el);
                    break;
                }
            }
        }

        return decodedText.toString().replace('q', ' ');
    }

    private String adjustKey(String text, String key) {
        StringBuilder result = new StringBuilder();

        int how_many_to_repeat = text.length() / key.length();
        int modulo = text.length() % key.length();

        result.append(key.repeat(how_many_to_repeat));
        result.append(key, 0, modulo);

        System.out.println(result);
        return result.toString();
    }

    private char[][] setupVarTab() {
        int size = 'z' - 'a' + 1;
        char[][] result2 = new char[size][size];
        char rowHeader = 'a';
        for (int row = 0; row < size; row++) {
            char val = rowHeader;
            for (int col = 0; col < size; col++) {
                if (val == ('z' + 1)) {
                    val = 'a';
                }
                result2[row][col] = val;
                val++;
            }
            rowHeader++;
        }
        return result2;
    }

    private void generateLetterIndexMap() {
        int index = 0;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            letterIndexMap.put(letter, index);
            index++;
        }
    }

    private void printTab(char[][] tab) {
        for (char[] chars : tab) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.print(chars[j] + ", ");
            }
            System.out.println();
        }
    }
}
