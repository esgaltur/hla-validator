/*
 * Multiple Allele Code Service
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.5
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package cz.esgaltur.hlavalidator.hlavalidator.nmdp.nmdp_web_service.client.invoker;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.FieldPosition;
import java.util.Date;


public class RFC3339DateFormat extends StdDateFormat {

    // Same as ISO8601DateFormat but serializing milliseconds.
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        String value = super.format(date);
        toAppendTo.append(value);
        return toAppendTo;
    }

}