package tr.com.nihatalim.tjk.parser.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueModel {
    private String key;
    private String value;

    private KeyValueModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static <T> KeyValueModel of(String[] params) {
        return new KeyValueModel(params[0], params[1]);
    }
}
