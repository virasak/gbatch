cns.each { c ->
  c.tables.each { t ->
    t.domainObjectName = toDomainObjectName(t.name)
  }
}


def toDomainObjectName(value) {
  value
}
