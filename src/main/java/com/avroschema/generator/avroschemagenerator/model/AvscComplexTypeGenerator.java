package com.avroschema.generator.avroschemagenerator.model;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.SchemaBuilder.FieldAssembler;
import org.apache.avro.SchemaBuilder.GenericDefault;
import org.apache.avro.SchemaBuilder.TypeBuilder;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;



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
				
				TypeBuilder<Schema> builder = isOptional() 
						? (TypeBuilder<Schema>) SchemaBuilder.builder(namespace).nullable() 
						: SchemaBuilder.builder(namespace);
				aliases = ListUtils.emptyIfNull(aliases);
				FieldAssembler<Schema> myFieldsAssembler = builder
						.record(name)
						.aliases(aliases.toArray(new String[aliases.size()]))
						.doc(doc)
						.fields();				
				
				getChildren()
				.stream()
				.forEach(child -> {GenericDefault<Schema> genericDefault = myFieldsAssembler
						.name(child.getName())
						.aliases(Optional.ofNullable(child.getAliases()).map(list -> list.toArray(new String[list.size()])).orElse(new String[0]))
						.doc(child.getDoc())
						.type(child.generate());
				        
				        if(child.getDefaultValue() != null) {
				        	genericDefault.withDefault(child.getDefaultValue());				        	
				        }else {
				        	genericDefault.noDefault();
				        }
				});				
				 return myFieldsAssembler.endRecord();
			}

			@Override
			public Schema ENUM() {
				return SchemaBuilder
						.enumeration(getName())
						.namespace(getNamespace())
						.doc(getDoc())
						.aliases(getAliases().toArray(new String[getAliases().size()]))					
						.symbols(getChildren().stream()
								.map(AvscTypeGenerator::getName)
								.collect(Collectors.toList())
								.toArray(new String[getChildren().size()]));
			}

			@Override
			public Schema ARRAY() {
				return SchemaBuilder.array().items(getChildren().stream()
						.findAny()
						.map(child -> child.generate())
						.orElseThrow(() -> new RuntimeException("Arrays must have only 1 field")));
			}
		});
	}

	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder extends AvscTypeGenerator.Builder<Builder>{
		
		AvscComplexTypeGenerator instance = new AvscComplexTypeGenerator();
		
		public Builder setNamespace(final String namespace) {
			instance.namespace = namespace;
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
			Assert.state(!ListUtils.emptyIfNull(instance.getChildren()).isEmpty(), "complex types must have at least 1 child");
			Assert.state(StringUtils.isNotBlank(instance.namespace), "namespace must be valued for complex types");
			return (AvscComplexTypeGenerator) super.build();
		}


		@Override
		protected AvscTypeGenerator getInstance() {
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
