# SqlDelight 2.2.x Postgresql trigger support

Experimental SqlDelight 2.2.x Postgresql Trigger support.

Support for triggers is still experimental. 

See `src/main/sqldelight/griffio/migrations/V1__Initial_version.sqm` for all supported syntax.

Best used with migration `.sqm` files.

Triggers and Triggers functions are defined in the same `.sqm` file.

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
