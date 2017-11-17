package com.avroschema.generator.avroschemagenerator.model;


import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;



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

			@Override
			public Schema ARRAY() {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder extends AvscTypeGenerator.Builder<Builder> {
		AvscBaseTypeGenerator instance = new AvscBaseTypeGenerator();
		
		
		public AvscBaseTypeGenerator build() {
			return (AvscBaseTypeGenerator) super.build();
		}
		
		public Builder addChildren(final List<AvscTypeGenerator> children){
			getInstance().children = new ArrayList<>();
			return this;
		}


		@Override
		protected AvscTypeGenerator getInstance() {
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
