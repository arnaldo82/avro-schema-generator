package com.avroschema.generator.avroschemagenerator.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.SchemaBuilder.FieldAssembler;
import org.apache.avro.SchemaBuilder.GenericDefault;
import org.apache.commons.collections4.ListUtils;


public class AvscComplexTypeGenerator extends AvscTypeGenerator {


	protected String namespace;
	
	private AvscComplexTypeGenerator() {
		
	}
	
	public String getNamespace() {
		return namespace;
	}

	@Override
	public Schema generate() {
		// TODO Auto-generated method stub
		return this.getType().apply(new AvroTypes.AvroTypeStrategy<Schema>() {

			@Override
			public Schema STRING() {
				return null;
			}

			@Override
			public Schema INTEGER() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Schema BYTES() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Schema BOOLEAN() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Schema RECORD() {
				
				FieldAssembler<Schema> myFieldsAssembler = SchemaBuilder.builder(namespace)
						.record(name)
						.aliases(aliases.toArray(new String[aliases.size()]))
						.doc(doc)
						.fields();
				
				
				getChildren()
				.stream()
				.forEach(child -> {GenericDefault<Schema> genericDefault = myFieldsAssembler
						.name(child.getName())
						.aliases(child.getAliases().toArray(new String[child.getAliases().size()]))
						.doc(child.getDoc())
						.type(child.generate());
				        
				        if(child.getDefaultValue() != null) {
				        	genericDefault.withDefault(child.getDefaultValue());				        	
				        }else {
				        	genericDefault.noDefault();
				        }
				});
				
				 return myFieldsAssembler.endRecord();
				
				// BASIC IMPLEMENTATION WITHOUT ASSEMBLER
//				List<Field> fields = getChildren()
//						.stream()
//						.map(child ->  new Schema.Field(child.getName(), child.generate(), child.getDoc(), null))
//						.collect(Collectors.toList());
//				return Schema.createRecord(name, doc, namespace, false, fields);
			}

			@Override
			public Schema ENUM() {
				return null;//SchemaBuilder.enumeration(getName()).symbols(getChildren());
			}
		});
	}

	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		AvscComplexTypeGenerator instance = new AvscComplexTypeGenerator();
		
		public Builder setNamespace(final String namespace) {
			instance.namespace = namespace;
			return this;
		}
		
		public Builder setName(final String name) {
			instance.name = name;
			return this;
		}

		public Builder setDoc(final String doc) {
			instance.doc = doc;
			return this;
		}	
		
		public Builder setAlias(final List<String> aliases) {
			instance.aliases = ListUtils.emptyIfNull(aliases);
			return this;
		}	
		
		public Builder addChildren(final List<AvscTypeGenerator> children){
			instance.children = ListUtils.emptyIfNull(children);
			return this;
		}
		
		public Builder setType(final AvroTypes type) {
			instance.type = type;
			return this;
		}	

		
		public AvscComplexTypeGenerator build() {
			return instance;
		}
		
	}

	@Override
	public String toString() {
		return "AvscComplexTypeGenerator [namespace=" + namespace + ", name=" + name + ", aliases=" + aliases
				+ ", nullable=" + nullable + ", optional=" + optional + ", defaultValue=" + defaultValue + ", doc="
				+ doc + ", type=" + type + ", children=" + children + "]";
	}



}
