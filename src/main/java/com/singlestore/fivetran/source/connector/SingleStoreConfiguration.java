package com.singlestore.fivetran.source.connector;

import java.util.Map;

public class SingleStoreConfiguration {

  private final String host;
  private final Integer port;
  private final String database;
  private final String table;
  private final String user;
  private final String password;
  private final String sslMode;
  private final String sslServerCert;
  private final String driverParameters;

  SingleStoreConfiguration(Map<String, String> conf) {
    this.host = conf.get("host");
    this.port = Integer.valueOf(conf.get("port"));
    this.database = conf.get("database");
    this.table = conf.get("table");
    this.user = conf.get("user");
    this.password = withDefaultNull(conf.get("password"));
    this.sslMode = withDefault(conf.get("ssl.mode"), "disable");
    this.sslServerCert = formatServerCert(withDefaultNull(conf.get("ssl.server.cert")));
    this.driverParameters = withDefaultNull(conf.get("driver.parameters"));
  }

  private String formatServerCert(String cert) {
    if (cert == null) {
      return cert;
    }

    return cert.replace("-----BEGIN CERTIFICATE-----", "-----BEGIN-CERTIFICATE-----")
        .replace("-----END CERTIFICATE-----", "-----END-CERTIFICATE-----")
        .replace(" ", "\n")
        .replace("-----BEGIN-CERTIFICATE-----", "-----BEGIN CERTIFICATE-----")
        .replace("-----END-CERTIFICATE-----", "-----END CERTIFICATE-----");
  }

  private String withDefault(String s, String def) {
    if (s == null || s.isEmpty()) {
      return def;
    }

    return s;
  }

  private String withDefaultNull(String s) {
    return withDefault(s, null);
  }

  public String host() {
    return host;
  }

  public Integer port() {
    return port;
  }

  public String database() {
    return database;
  }

  public String table() {
    return table;
  }

  public String user() {
    return user;
  }

  public String password() {
    return password;
  }

  public String sslMode() {
    return sslMode;
  }

  public String sslServerCert() {
    return sslServerCert;
  }

  public String driverParameters() {
    return driverParameters;
  }
}
