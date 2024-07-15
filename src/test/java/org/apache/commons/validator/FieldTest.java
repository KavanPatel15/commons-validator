package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test <code>Field</code> objects.
 */
public class FieldTest {

    protected Field field;

    /**
     * Test setup
     */
    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    /**
     * Test clean up
     */
    @AfterEach
    public void tearDown() {
        field = null;
    }

    /**
     * Create an argument with the given key, name, and position.
     */
    private Arg createArg(final String key, final String name, final int position) {
        final Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }

    /**
     * Create an argument with the given key and position.
     */
    private Arg createArg(final String key, final int position) {
        return createArg(key, null, position);
    }

    /**
     * Create an argument with the given key and name.
     */
    private Arg createArg(final String key, final String name) {
        return createArg(key, name, -1);
    }

    /**
     * Create an argument with the given key.
     */
    private Arg createArg(final String key) {
        return createArg(key, null, -1);
    }

    /**
     * Test Field with only 'default' arguments, position specified for one argument
     */
    @Test
    public void testDefaultOnePosition() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertEquals(4, field.getArgs("required").length, "testDefaultOnePosition(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testDefaultOnePosition(2) ");
        assertNull(field.getArg("required", 1), "testDefaultOnePosition(3) ");
        assertEquals("default-position-2", field.getArg("required", 2).getKey(), "testDefaultOnePosition(4) ");
        assertEquals("default-position-3", field.getArg("required", 3).getKey(), "testDefaultOnePosition(5) ");
    }

    /**
     * Test Field with only 'default' arguments, no positions specified.
     */
    @Test
    public void testDefaultPositionImplied() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals(3, field.getArgs("required").length, "testDefaultPositionImplied(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testDefaultPositionImplied(2) ");
        assertEquals("default-position-1", field.getArg("required", 1).getKey(), "testDefaultPositionImplied(3) ");
        assertEquals("default-position-2", field.getArg("required", 2).getKey(), "testDefaultPositionImplied(4) ");
    }

    /**
     * Test Field with only 'default' arguments, some position specified.
     */
    @Test
    public void testDefaultSomePositions() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals(4, field.getArgs("required").length, "testDefaultSomePositions(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testDefaultSomePositions(2) ");
        assertEquals("default-position-1", field.getArg("required", 1).getKey(), "testDefaultSomePositions(3) ");
        assertEquals("default-position-2", field.getArg("required", 2).getKey(), "testDefaultSomePositions(4) ");
        assertEquals("default-position-3", field.getArg("required", 3).getKey(), "testDefaultSomePositions(5) ");
    }

    /**
     * Test Field with only 'default' arguments, positions specified.
     */
    @Test
    public void testDefaultUsingPositions() {
        field.addArg(createArg("default-position-1", 1));
        field.addArg(createArg("default-position-0", 0));
        field.addArg(createArg("default-position-2", 2));

        assertEquals(3, field.getArgs("required").length, "testDefaultUsingPositions(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testDefaultUsingPositions(2) ");
        assertEquals("default-position-1", field.getArg("required", 1).getKey(), "testDefaultUsingPositions(3) ");
        assertEquals("default-position-2", field.getArg("required", 2).getKey(), "testDefaultUsingPositions(4) ");
    }

    /**
     * Test Field with no arguments
     */
    @Test
    public void testEmptyArgs() {
        assertEquals(0, field.getArgs("required").length, "Empty Args(1) ");
    }

    /**
     * Test Field with a 'default' argument overridden without positions specified.
     */
    @Test
    public void testOverridePositionImplied() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));

        assertEquals(3, field.getArgs("required").length, "testOverridePositionImplied(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testOverridePositionImplied(2) ");
        assertEquals("required-position-1", field.getArg("required", 1).getKey(), "testOverridePositionImplied(3) ");
        assertEquals("required-position-2", field.getArg("required", 2).getKey(), "testOverridePositionImplied(4) ");

        assertEquals(3, field.getArgs("mask").length, "testOverridePositionImplied(5) ");
        assertEquals("default-position-0", field.getArg("mask", 0).getKey(), "testOverridePositionImplied(6) ");
        assertEquals("mask-position-1", field.getArg("mask", 1).getKey(), "testOverridePositionImplied(7) ");
        assertNull(field.getArg("mask", 2), "testOverridePositionImplied(8) ");

        assertEquals("default-position-0", field.getArg(0).getKey(), "testOverridePositionImplied(9) ");
        assertNull(field.getArg(1), "testOverridePositionImplied(10) ");
        assertNull(field.getArg(2), "testOverridePositionImplied(11) ");
    }

    /**
     * Test Field with a 'default' argument overridden with some positions specified
     */
    @Test
    public void testOverrideSomePosition() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertEquals(4, field.getArgs("required").length, "testOverrideSomePosition(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testOverrideSomePosition(2) ");
        assertEquals("required-position-1", field.getArg("required", 1).getKey(), "testOverrideSomePosition(3) ");
        assertEquals("required-position-2", field.getArg("required", 2).getKey(), "testOverrideSomePosition(4) ");
        assertNull(field.getArg("required", 3), "testOverrideSomePosition(5) ");

        assertEquals(4, field.getArgs("mask").length, "testOverrideSomePosition(6) ");
        assertEquals("default-position-0", field.getArg("mask", 0).getKey(), "testOverrideSomePosition(7) ");
        assertEquals("default-position-1", field.getArg("mask", 1).getKey(), "testOverrideSomePosition(8) ");
        assertEquals("default-position-2", field.getArg("mask", 2).getKey(), "testOverrideSomePosition(9) ");
        assertEquals("mask-position-3", field.getArg("mask", 3).getKey(), "testOverrideSomePosition(10) ");

        assertEquals("default-position-0", field.getArg(0).getKey(), "testOverrideSomePosition(11) ");
        assertEquals("default-position-1", field.getArg(1).getKey(), "testOverrideSomePosition(12) ");
        assertEquals("default-position-2", field.getArg(2).getKey(), "testOverrideSomePosition(13) ");
        assertNull(field.getArg(3), "testOverrideSomePosition(14) ");
    }

    /**
     * Test Field with a 'default' argument overridden using 'position' property
     */
    @Test
    public void testOverrideUsingPositionA() {
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));

        assertEquals(3, field.getArgs("required").length, "testOverrideUsingPositionA(1) ");
        assertEquals("required-position-1", field.getArg("required", 1).getKey(), "testOverrideUsingPositionA(2) ");

        assertEquals(3, field.getArgs("mask").length, "testOverrideUsingPositionA(3) ");
        assertEquals("default-position-1", field.getArg("mask", 1).getKey(), "testOverrideUsingPositionA(4) ");

        assertEquals("default-position-1", field.getArg(1).getKey(), "testOverrideUsingPositionA(5) ");
    }

    /**
     * Test Field with a 'default' argument overridden using 'position' property
     */
    @Test
    public void testOverrideUsingPositionB() {
        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals(4, field.getArgs("required").length, "testOverrideUsingPositionB(1) ");
        assertEquals("default-position-0", field.getArg("required", 0).getKey(), "testOverrideUsingPositionB(2) ");
        assertEquals("required-position-1", field.getArg("required", 1).getKey(), "testOverrideUsingPositionB(3) ");
        assertEquals("default-position-2", field.getArg("required", 2).getKey(), "testOverrideUsingPositionB(4) ");
        assertEquals("required-position-3", field.getArg("required", 3).getKey(), "testOverrideUsingPositionB(5) ");

        assertEquals(4, field.getArgs("mask").length, "testOverrideUsingPositionB(6) ");
        assertEquals("default-position-0", field.getArg("mask", 0).getKey(), "testOverrideUsingPositionB(6) ");
        assertEquals("default-position-1", field.getArg("mask", 1).getKey(), "testOverrideUsingPositionB(7) ");
        assertEquals("default-position-2", field.getArg("mask", 2).getKey(), "testOverrideUsingPositionB(8) ");
        assertNull(field.getArg("mask", 3), "testOverrideUsingPositionB(9) ");
    }
}
