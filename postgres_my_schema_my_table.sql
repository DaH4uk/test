CREATE SCHEMA my_schema;
CREATE TABLE my_schema.my_table
(
  file_id SERIAL PRIMARY KEY NOT NULL,
  filename VARCHAR NOT NULL,
  url VARCHAR NOT NULL
);
CREATE UNIQUE INDEX my_table_file_id_uindex ON my_schema.my_table (file_id);
CREATE UNIQUE INDEX my_table_filename_uindex ON my_schema.my_table (filename);

