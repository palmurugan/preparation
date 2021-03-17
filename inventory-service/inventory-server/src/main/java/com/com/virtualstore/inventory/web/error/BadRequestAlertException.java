package com.com.virtualstore.inventory.web.error;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/** @author palmurugan BadRequestAlert exception to handle all the bad requsts */
public class BadRequestAlertException extends AbstractThrowableProblem {

  private static final URI DEFAULT_URI = URI.create("https://virtualstore.com/");
  private final String entityName;
  private final String errorKey;

  public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
    this(DEFAULT_URI, defaultMessage, entityName, errorKey);
  }

  public BadRequestAlertException(
      URI type, String defaultMessage, String entityName, String errorKey) {
    super(
        type,
        defaultMessage,
        Status.BAD_REQUEST,
        null,
        null,
        null,
        getAlertParameters(entityName, errorKey));
    this.entityName = entityName;
    this.errorKey = errorKey;
  }

  private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
    Map<String, Object> parameterMap = new HashMap<>();
    parameterMap.put("message", "error." + errorKey);
    parameterMap.put("params", entityName);
    return parameterMap;
  }

  public String getEntityName() {
    return entityName;
  }

  public String getErrorKey() {
    return errorKey;
  }
}
