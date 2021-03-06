/*******************************************************************************
 * Copyright (C) 2005-2014 Alfresco Software Limited.
 *
 * This file is part of Alfresco Mobile for Android.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.alfresco.mobile.android.application.ui.form.validation;

import java.math.BigInteger;

import org.alfresco.mobile.android.api.model.config.ConfigConstants;
import org.alfresco.mobile.android.api.model.config.ValidationConfig;

import android.content.Context;

public class StringLengthValidationRule extends ValidationRule
{
    BigInteger min;

    BigInteger max;

    // ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    // ///////////////////////////////////////////////////////////////////////////
    public StringLengthValidationRule(Context context, ValidationConfig configuration)
    {
        super(context, configuration);
        min = (BigInteger) validationConfig.getParameter(ConfigConstants.MIN_VALUE);
        max = (BigInteger) validationConfig.getParameter(ConfigConstants.MAX_VALUE);

        errorMessage = String.format(errorMessage, (min != null) ? min.toString() : 0, (max != null) ? max.toString()
                : 2048);
    }

    // ///////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean isValid(Object object)
    {
        if (object instanceof String)
        {
            int size = ((String) object).length();

            if (min != null && max != null)
            {
                return min.intValue() <= size && max.intValue() >= size;
            }
            else if (min != null && max == null)
            {
                return min.intValue() <= size;
            }
            else if (min == null && max != null) { return max.intValue() >= size; }
            return false;
        }
        else
        {
            return false;
        }
    }
}
