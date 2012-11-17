package com.javiermoreno.holamundojpa;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class CibNamingStrategy extends ImprovedNamingStrategy {

	@Override
	public String classToTableName(String className) {
		return "CIB_" + super.classToTableName(className);
	}

	@Override
	public String tableName(String tableName) {
		return "CIB_" + super.tableName(tableName);
	}
	
	@Override
	public String columnName(String columnName) {
		return "CIB_" + super.columnName(columnName);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return "CIB_" + super.propertyToColumnName(propertyName);
	}

	@Override
	public String collectionTableName(String ownerEntity,
			String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		return "CIB_" + super.collectionTableName(ownerEntity, ownerEntityTable,
				associatedEntity, associatedEntityTable, propertyName);
	}

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		return "CIB_" + super.foreignKeyColumnName(propertyName, propertyEntityName,
				propertyTableName, referencedColumnName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return "CIB_" + super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	@Override
	public String logicalCollectionColumnName(String columnName,
			String propertyName, String referencedColumn) {
		return "CIB_" + super.logicalCollectionColumnName(columnName, propertyName,
				referencedColumn);
	}

	@Override
	public String logicalCollectionTableName(String tableName,
			String ownerEntityTable, String associatedEntityTable,
			String propertyName) {
		return "CIB_" + super.logicalCollectionTableName(tableName, ownerEntityTable,
				associatedEntityTable, propertyName);
	}

	@Override
	public String logicalColumnName(String columnName, String propertyName) {
		return "CIB_" + super.logicalColumnName(columnName, propertyName);
	}

	
	

}
