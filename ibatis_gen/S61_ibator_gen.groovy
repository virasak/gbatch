def sqlmapBuilder = new groovy.xml.MarkupBuilder(new PrintWriter(System.out))

sqlmapBuilder.ibatorConfiguration() {

  javaModelGenerator(targetPackage:'model', targetProject:'projectName') {
    property(name:'enableSubPackage', value:'true')
    property(name:'trimStrings', value:'true')
  }

  sqlMapGenerator(targetPackage:'xml', targetProject:'projectName') {
    property(name:'enableSubPackages', value:'true')
  }

  daoMapGenerator(type:'SPRING', targetPackage:'xml', targetProject:'projectName') {
    property(name:'enableSubPackages', value:'true')
  }

  cns.each { c ->
    c.tables.each { t ->
      table(catalog:c.catalog, schema:c.schema, tableName:t.name, domainObjectName:t.domainObjectName) {
        t.columns.each { col ->
          columnOverride(property:col.name, javaType:col.type, column:col.name, jdbcType:col.type)
        }
      }
    }
  }
}
