// 
cns = []

def metaData = groovy.sql.Sql.newInstance(
                 jdbc.url,
                 jdbc.userName,
                 jdbc.password,
                 jdbc.driverClassName).connection.metaData

// list all catalog and schema
metaData.schemas.with {
  while(next()) {
    cns << [catalog:getString('TABLE_CAT'), schema:getString('TABLE_SCHEM')]
  }
}

// if not found, all table don't belong to any schema
if (!cns) {
  cns << [catalog:null, schema:null]
}

// list all tables
cns.each { c ->
  c.tables = []
  metaData.getTables(c.catalog, c.schema, null, null).with {
    while(next()) {
      c.tables << [name:getString('TABLE_NAME')]
    }
  }
}

// for each table, list column properties
cns.each { c ->
  c.tables.each { table ->
    table.columns = []
    metaData.getColumns(c.catalog, c.schema, table.name, null).with { 
      while(next()) {
        table.columns << [name:getString('COLUMN_NAME'), type:getString('DATA_TYPE')]
      }
    }
  }
}
