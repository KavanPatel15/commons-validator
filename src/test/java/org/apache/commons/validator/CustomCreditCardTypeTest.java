/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test custom implementation of CreditCardType.
 */
public class CustomCreditCardTypeTest {

    private static class DinersClub implements CreditCardValidator.CreditCardType {
        private static final String PREFIX = "300,301,302,303,304,305,";

        @Override
        public boolean matches(final String card) {
            final String prefix = card.substring(0, 3) + ",";
            return PREFIX.contains(prefix) && card.length() == 14;
        }
    }

    private static final String VALID_DINERS = "30569309025904";

    @Test
    public void testCustomCardType() {
        final CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.NONE);
        ccv.addAllowedCardType(new DinersClub());
        assertTrue(ccv.isValid(VALID_DINERS));
    }
}

