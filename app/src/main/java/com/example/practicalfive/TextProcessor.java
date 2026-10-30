package com.example.practicalfive;

/**
 * Validates and normalises the municipality names entered by the user.
 *
 * <p>The class is a plain Java class: it does not extend {@code Activity} and has
 * no associated XML layout, so the same validation logic can be reused by both
 * screens without duplicating it.</p>
 */
public class TextProcessor {

    /** Supported municipalities, already in the correct capitalisation. */
    private static final String[] SUPPORTED_MUNICIPALITIES = {
            "Beijing", "Shanghai", "Chongqing", "Tianjin"
    };

    /**
     * Validates a municipality name and returns it with the correct capitalisation.
     *
     * @param municipality the raw text entered by the user
     * @return the correctly capitalised name, or {@code null} when the input is
     *         null, empty, whitespace only or not one of the supported municipalities
     */
    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        for (String supported : SUPPORTED_MUNICIPALITIES) {
            if (supported.equalsIgnoreCase(trimmed)) {
                return supported;
            }
        }

        return null;
    }

    /**
     * @param municipality a name returned by {@link #processMunicipality(String)}
     * @return the position of the municipality in {@code municipalities_array},
     *         or -1 when the name is not supported
     */
    public int indexOfMunicipality(String municipality) {
        if (municipality == null) {
            return -1;
        }

        for (int i = 0; i < SUPPORTED_MUNICIPALITIES.length; i++) {
            if (SUPPORTED_MUNICIPALITIES[i].equals(municipality)) {
                return i;
            }
        }

        return -1;
    }
}
