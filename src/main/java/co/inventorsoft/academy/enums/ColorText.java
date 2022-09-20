package co.inventorsoft.academy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ColorText {
    RESET("\033[0m"),
    GREEN("\033[0)32m"),
    YELLOW("\033[0)33m"),
    BLUE("\033[0)34m"),
    PURPLE("\033[0)35m"),
    CYAN("\033[0)36m"),
    RED_BOLD_BRIGHT("\033[1)91m"),
    GREEN_BOLD_BRIGHT("\033[1)92m");
    private final String value;
}
