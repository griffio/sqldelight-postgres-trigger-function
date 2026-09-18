# SqlDelight 2.4.0 Postgresql trigger support

* Experimental SqlDelight 2.4.0 Postgresql Trigger support.

Support for triggers is still experimental. 

See `src/main/sqldelight/griffio/migrations/V1__Initial_version.sqm` for all supported syntax.

Best used with migration `.sqm` files as the ordering of statements is preserved.

Triggers and Triggers functions must be defined in the same `.sqm` file.

* Syntax

`CREATE OR REPLACE FUNCTION ...` and `CREATE OR REPLACE TRIGGER ...`

The trigger function must be declared before the trigger statement as Postgresql requires the function to be defined before it is referenced.

`new` and `old` variables are available in the trigger body.

Conditional blocks `IF THEN ... ELSIF THEN ... ELSE END IF`.

Trigger variables `TG_OP` Etc are available in the trigger body.

Use `DROP TRIGGER IF EXISTS ...` and `DROP FUNCTION IF EXISTS ...` to remove triggers and functions.

```shell
createdb trigger-examples &&
./gradlew build &&
./gradlew flywayMigrate
```

* Support `RAISE`statment - 
  * MERGED https://github.com/sqldelight/sqldelight/pull/6297
 
  * `RAISE EXCEPTION | WARNING | NOTICE 'message %', arg [USING ERRCODE = '...']`

* Support `INSERT`, `UPDATE` and `DELETE` statements in the trigger body with `IF FOUND` / `IF NOT FOUND`.

See `orders_reduce_stock` in `V1__Initial_version.sqm` for `RAISE` followed by `UPDATE` in the same trigger function.
