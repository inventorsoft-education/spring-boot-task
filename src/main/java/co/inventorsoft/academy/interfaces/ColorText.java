package co.inventorsoft.academy.interfaces;

public interface ColorText {
    // Reset
    String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    String BLACK = "\033[0;30m";   // BLACK
    String RED = "\033[0;31m";     // RED
    String GREEN = "\033[0;32m";   // GREEN
    String YELLOW = "\033[0;33m";  // YELLOW
    String BLUE = "\033[0;34m";    // BLUE
    String PURPLE = "\033[0;35m";  // PURPLE
    String CYAN = "\033[0;36m";    // CYAN
    String WHITE = "\033[0;37m";   // WHITE

    // Bold High Intensity
    String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

}
