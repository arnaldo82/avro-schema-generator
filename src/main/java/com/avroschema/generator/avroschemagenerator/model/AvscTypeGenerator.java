package com.avroschema.generator.avroschemagenerator.model;

import java.util.List;

import org.apache.avro.Schema;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;


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

	public static abstract class Builder<T extends Builder<?>> {
		protected abstract AvscTypeGenerator getInstance();
				
		@SuppressWarnings("unchecked")
		public T setName(final String name) {
			getInstance().name = name;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T setDoc(final String doc) {
			getInstance().doc = doc;
			return (T) this;
		}	
		
		@SuppressWarnings("unchecked")
		public T setAlias(final List<String> aliases) {
			getInstance().aliases = ListUtils.emptyIfNull(aliases);
			return (T) this;
		}	
				
		@SuppressWarnings("unchecked")
		public T setType(final AvroTypes type) {
			getInstance().type = type;
			return (T) this;
		}	

		@SuppressWarnings("unchecked")
		public T setOptional() {
			getInstance().optional = Boolean.TRUE;
			return (T) this;
		}
		
		@SuppressWarnings("unchecked")
		public T setDefaultValue(final Object defaultValue) {
			getInstance().defaultValue = defaultValue;
			return (T) this;
		}
				
		@SuppressWarnings("unchecked")
		public T setNullable() {
			getInstance().nullable = Boolean.TRUE;
			return (T) this;
		}
		
		@SuppressWarnings("unchecked")
		public T addChildren(final List<AvscTypeGenerator> children){
			getInstance().children = ListUtils.emptyIfNull(children);
			return (T) this;
		}

		
		public AvscTypeGenerator build() {
			Assert.state(StringUtils.isNotBlank(getInstance().getName()), "name can't be empty");
			Assert.state(getInstance().getType() != null, "type must not be null");
			return getInstance();
		}
	}

}
