package com.example.practicalfive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Local unit tests for the input validation logic. They run on the development
 * machine with {@code ./gradlew test} and do not need a device.
 */
public class TextProcessorTest {

    private final TextProcessor textProcessor = new TextProcessor();

    @Test
    public void acceptsLowerCaseNames() {
        assertEquals("Beijing", textProcessor.processMunicipality("beijing"));
        assertEquals("Shanghai", textProcessor.processMunicipality("shanghai"));
        assertEquals("Chongqing", textProcessor.processMunicipality("chongqing"));
        assertEquals("Tianjin", textProcessor.processMunicipality("tianjin"));
    }

    @Test
    public void acceptsUpperCaseNames() {
        assertEquals("Beijing", textProcessor.processMunicipality("BEIJING"));
        assertEquals("Shanghai", textProcessor.processMunicipality("SHANGHAI"));
    }

    @Test
    public void acceptsMixedCaseNames() {
        assertEquals("Chongqing", textProcessor.processMunicipality("ChOnGqInG"));
        assertEquals("Tianjin", textProcessor.processMunicipality("tIaNjIn"));
    }

    @Test
    public void ignoresSurroundingWhitespace() {
        assertEquals("Beijing", textProcessor.processMunicipality("  Beijing  "));
        assertEquals("Shanghai", textProcessor.processMunicipality("\tShanghai\n"));
    }

    @Test
    public void rejectsUnsupportedMunicipalities() {
        assertNull(textProcessor.processMunicipality("Guangzhou"));
        assertNull(textProcessor.processMunicipality("Shenzhen"));
    }

    @Test
    public void rejectsNullEmptyAndBlankInput() {
        assertNull(textProcessor.processMunicipality(null));
        assertNull(textProcessor.processMunicipality(""));
        assertNull(textProcessor.processMunicipality("    "));
        assertNull(textProcessor.processMunicipality("\t\n"));
    }

    @Test
    public void summaryIndexFollowsArrayOrder() {
        assertEquals(0, textProcessor.indexOfMunicipality("Beijing"));
        assertEquals(1, textProcessor.indexOfMunicipality("Shanghai"));
        assertEquals(2, textProcessor.indexOfMunicipality("Chongqing"));
        assertEquals(3, textProcessor.indexOfMunicipality("Tianjin"));
        assertEquals(-1, textProcessor.indexOfMunicipality("Guangzhou"));
        assertEquals(-1, textProcessor.indexOfMunicipality(null));
    }
}
