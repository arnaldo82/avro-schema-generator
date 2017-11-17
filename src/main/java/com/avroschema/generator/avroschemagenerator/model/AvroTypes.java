package com.avroschema.generator.avroschemagenerator.model;

import org.apache.avro.Schema;

public enum AvroTypes {
	
	STRING(Schema.Type.STRING) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.STRING();
		}
	},
	INTEGER(Schema.Type.INT) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.INTEGER();
		}
	},
	BYTES(Schema.Type.BYTES) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.BYTES();
		}
	},
	BOOLEAN(Schema.Type.BOOLEAN) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.BOOLEAN();
		}
	},
	RECORD(Schema.Type.RECORD) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.RECORD();
		}
	},
	ENUM(Schema.Type.ENUM) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.ENUM();
		}
	},
	ARRAY(Schema.Type.ARRAY) {
		@Override
		public <T> T apply(AvroTypeStrategy<T> strategy) {
			return strategy.ARRAY();
		}
	};
	
	
	
	private Schema.Type avroType;
	
	private AvroTypes(Schema.Type avroType){
		this.avroType = avroType;
	}
	
	public Schema.Type getAvroType() {
		return avroType;
	}
	
    public abstract <T> T apply(AvroTypes.AvroTypeStrategy<T> strategy);
    
    public interface AvroTypeStrategy<T> {
        
        public T STRING();
        
        public T INTEGER();
        
        public T BYTES();
        
        public T BOOLEAN();
        
        public T RECORD();
        
        public T ENUM();
        
        public T ARRAY();
        
    }	

}
