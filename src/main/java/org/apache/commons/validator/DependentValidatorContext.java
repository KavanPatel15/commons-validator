package org.apache.commons.validator;

import java.util.Map;

public class DependentValidatorContext {
    private final ValidatorAction va;
    private final ValidatorResults results;
    private final Map<String, ValidatorAction> actions;
    private final Map<String, Object> params;
    private final int pos;

    public DependentValidatorContext(ValidatorAction va, ValidatorResults results,
                                     Map<String, ValidatorAction> actions,
                                     Map<String, Object> params, int pos) {
        this.va = va;
        this.results = results;
        this.actions = actions;
        this.params = params;
        this.pos = pos;
    }

    public ValidatorAction getVa() {
        return va;
    }

    public ValidatorResults getResults() {
        return results;
    }

    public Map<String, ValidatorAction> getActions() {
        return actions;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public int getPos() {
        return pos;
    }
}

