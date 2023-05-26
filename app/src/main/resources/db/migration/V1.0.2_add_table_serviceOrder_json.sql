CREATE TABLE IF NOT EXISTS public.service_order_json
(
      id character varying(250) NOT NULL PRIMARY KEY,
      data json NOT NULL,
      FOREIGN KEY (id) REFERENCES service_order (id) ON DELETE CASCADE
);