package co.inventorsoft.academy.interfaces;

public interface ColorText {
    /**
     * Reset
     */
    String RESET = "\033[0m";

    /**
     * Regular Colors
     */
    String GREEN = "\033[0;32m";
    String YELLOW = "\033[0;33m";
    String BLUE = "\033[0;34m";
    String PURPLE = "\033[0;35m";
    String CYAN = "\033[0;36m";

    /**
     * Bold High Intensity
      */
    String RED_BOLD_BRIGHT = "\033[1;91m";
    String GREEN_BOLD_BRIGHT = "\033[1;92m";
}
