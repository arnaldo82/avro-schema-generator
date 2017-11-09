package com.avroschema.generator.avroschemagenerator.model;


import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.commons.collections4.ListUtils;



public class AvscBaseTypeGenerator extends AvscTypeGenerator {
	
	
	private AvscBaseTypeGenerator() {}

	@Override
	public Schema generate() {
		return this.getType().apply(new AvroTypes.AvroTypeStrategy<Schema>() {

			@Override
			public Schema STRING() {
				return isNullable()
						? SchemaBuilder.builder().nullable().stringType()
						: SchemaBuilder.builder().stringType();
			}

			@Override
			public Schema INTEGER() {
				return 	isNullable()
							? SchemaBuilder.builder().nullable().intType()
							: SchemaBuilder.builder().intType();
			}

			@Override
			public Schema BYTES() {			
				return isNullable()
						? SchemaBuilder.builder().nullable().bytesType()
						: SchemaBuilder.builder().bytesType();
			}

			@Override
			public Schema BOOLEAN() {
				return isNullable()
						? SchemaBuilder.builder().nullable().booleanType()
						: SchemaBuilder.builder().booleanType();	
			}

			@Override
			public Schema RECORD() {
				return null;
			}

			@Override
			public Schema ENUM() {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		AvscBaseTypeGenerator instance = new AvscBaseTypeGenerator();
		
		
		public Builder setName(final String name) {
			instance.name = name;
			return this;
		}
		
		public Builder setType(final AvroTypes type) {
			instance.type = type;
			return this;
		}	

		public Builder setDoc(final String doc) {
			instance.doc = doc;
			return this;
		}	
		
		public Builder setAliases(final List<String> aliases) {
			instance.aliases = ListUtils.emptyIfNull(aliases);
			return this;
		}	
		
		public Builder setDefaultValue(final Object defaultValue) {
			instance.defaultValue = defaultValue;
			return this;
		}
		
		public Builder setOptional() {
			instance.optional = Boolean.TRUE;
			return this;
		}
		
		public Builder setNullable() {
			instance.nullable = Boolean.TRUE;
			return this;
		}

		public AvscBaseTypeGenerator build() {
			return instance;
		}	
	}

	@Override
	public String toString() {
		return "AvscBaseTypeGenerator [name=" + name + ", aliases=" + aliases + ", nullable=" + nullable + ", optional="
				+ optional + ", defaultValue=" + defaultValue + ", doc=" + doc + ", type=" + type + ", children="
				+ children + "]";
	}

	
}
