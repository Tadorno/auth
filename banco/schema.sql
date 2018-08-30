create table if not exists oauth.oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255),
  dt_creation TIMESTAMP DEFAULT now()
);

create table oauth.oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256),
  dt_creation TIMESTAMP DEFAULT now()
);

create table oauth.oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA,
  dt_creation TIMESTAMP DEFAULT now()
);
