package com.avroschema.generator.avroschemagenerator.model;

import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder.FieldAssembler;

public abstract class AvscTypeGenerator {
	
	protected String name;
	protected List<String> aliases;
	protected Boolean nullable = Boolean.FALSE;
	protected Boolean optional = Boolean.FALSE;
	protected Object defaultValue;
	protected String doc;
	protected AvroTypes type;	
	protected List<AvscTypeGenerator> children;
	
	public String getName() {
		return name;
	}

	public String getDoc() {
		return doc;
	}
	
	public List<String> getAliases() {
		return aliases;
	}

	public AvroTypes getType() {
		return type;
	}
	

	public Boolean isNullable() {
		return nullable;
	}

	public Boolean isOptional() {
		return optional;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}	

	public abstract Schema generate(); 
	
	public final List<AvscTypeGenerator> getChildren() {
		return children;
	}


}
