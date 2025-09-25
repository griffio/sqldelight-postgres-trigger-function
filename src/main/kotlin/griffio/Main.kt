package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample

import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/trigger-examples")
    applicationName = "App Main"
}.asJdbcDriver()

fun main() {
    val driver = getSqlDriver()

    val sample = Sample(driver)

    sample.organizationsQueries.insert("testing 1")
    sample.organizationsQueries.insert("testing 2")
    sample.organizationsQueries.insert("testing 3")

    sample.organizationsQueries.update("updated", 4)
}
