package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter


public class Input {
    private String textInput;
    private String password;
    private String textArea;
    private String dropdownSelect;
    private String dropdownDatalist;
    private String fileInput;
    private String colourPicker;
    private String datePicker;

    public static Input decode(Map<String, String> row) {
        return new Input
                (
                    row.get("textInput"),
                    row.get("password"),
                    row.get("textArea"),
                    row.get("dropdownSelect"),
                    row.get("dropdownDatalist"),
                    row.get("fileInput"),
                    row.get("colourPicker"),
                    row.get("datePicker")
                );
    }
}