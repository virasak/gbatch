row = { name, type -> "${name.padRight(40)} ${type.padLeft(20)}" }
line = "-"*80

cns.each { c ->
  c.tables.each { table ->
    println "${c.catalog} ${c.schema} ${table.name}"
    println line
    println row('Name', 'Type')
    println line
    table.columns.each { col ->
      println row(col.name, col.type)
    }
    println line
  }
}
