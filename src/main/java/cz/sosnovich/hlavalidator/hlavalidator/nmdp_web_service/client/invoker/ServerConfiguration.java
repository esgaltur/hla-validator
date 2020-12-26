package cz.sosnovich.hlavalidator.hlavalidator.nmdp_web_service.client.invoker;

import java.util.Map;

/**
 * Representing a Server configuration.
 */
public class ServerConfiguration {
    public final String url;
    public final String description;
    public final Map<String, ServerVariable> variables;

    /**
     * @param url         A URL to the target host.
     * @param description A describtion of the host designated by the URL.
     * @param variables   A map between a variable name and its value. The value is used for substitution in the server's URL template.
     */
    public ServerConfiguration(String url, String description, Map<String, ServerVariable> variables) {
        this.url = url;
        this.description = description;
        this.variables = variables;
    }

    /**
     * Format URL template using given variables.
     *
     * @param variables A map between a variable name and its value.
     * @return Formatted URL.
     */
    public String getUrl(Map<String, String> variables) {
        String urllocal = this.url;

        // go through variables and replace placeholders
        for (Map.Entry<String, ServerVariable> variable : this.variables.entrySet()) {
            String name = variable.getKey();
            ServerVariable serverVariable = variable.getValue();
            String value = serverVariable.defaultValue;

            if (variables != null && variables.containsKey(name)) {
                value = variables.get(name);
                if (serverVariable.enumValues.size() > 0 && !serverVariable.enumValues.contains(value)) {
                    throw new RuntimeException("The variable " + name + " in the server URL has invalid value " + value + ".");
                }
            }
            urllocal = urllocal.replaceAll("\\{" + name + "}", value);
        }
        return urllocal;
    }

    /**
     * Format URL template using default server variables.
     *
     * @return Formatted URL.
     */
    public String getUrl() {
        return getUrl(null);
    }
}
