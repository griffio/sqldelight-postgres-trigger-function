package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample
import org.postgresql.util.PSQLException

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

    println(sample.accountsQueries.insert(100.0).value)
    try {
        sample.accountsQueries.update(1.0, 1).value
    } catch (e: PSQLException) {
        println(e.sqlState)
    }

    // RAISE then UPDATE: the orders_reduce_stock trigger raises a NOTICE then updates products.stock
    val product = sample.productsQueries.insert("widget", 10).executeAsOne()
    println("product ${product.id} stock ${product.stock}")

    val order = sample.ordersQueries.insert(product.id, 3).executeAsOne()
    println("order ${order.id} quantity ${order.quantity}")
    println("product ${product.id} stock ${sample.productsQueries.selectById(product.id).executeAsOne().stock}")

    // RAISE EXCEPTION ... USING ERRCODE = 'check_violation' (23514) when the UPDATE affected no rows
    try {
        sample.ordersQueries.insert(product.id, 20).executeAsOne()
    } catch (e: PSQLException) {
        println("${e.sqlState} ${e.serverErrorMessage?.message}")
    }
}
