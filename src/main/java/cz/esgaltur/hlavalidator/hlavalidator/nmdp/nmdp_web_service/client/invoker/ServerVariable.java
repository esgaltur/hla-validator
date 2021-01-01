package cz.esgaltur.hlavalidator.hlavalidator.nmdp.nmdp_web_service.client.invoker;

import java.util.Set;

/**
 * Representing a Server Variable for server URL template substitution.
 */
public class ServerVariable {
    public final String description;
    public final String defaultValue;
    public final Set<String> enumValues;

    /**
     * @param description  A description for the server variable.
     * @param defaultValue The default value to use for substitution.
     * @param enumValues   An enumeration of string values to be used if the substitution options are from a limited set.
     */
    public ServerVariable(String description, String defaultValue, Set<String> enumValues) {
        this.description = description;
        this.defaultValue = defaultValue;
        this.enumValues = enumValues;
    }
}
