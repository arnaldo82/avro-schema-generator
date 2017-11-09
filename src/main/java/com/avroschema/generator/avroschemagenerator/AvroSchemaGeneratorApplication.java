package com.avroschema.generator.avroschemagenerator;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avroschema.generator.avroschemagenerator.model.AvroTypes;
import com.avroschema.generator.avroschemagenerator.model.AvscComplexTypeGenerator;
import com.avroschema.generator.avroschemagenerator.model.AvscBaseTypeGenerator;

@SpringBootApplication
public class AvroSchemaGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroSchemaGeneratorApplication.class, args);
		
		AvscBaseTypeGenerator field1 = AvscBaseTypeGenerator.getBuilder()
				.setAliases(Arrays.asList("old_field1", "old2_field1"))
				.setName("field1")
				.setType(AvroTypes.STRING)
				.setDefaultValue("default1")
				.setDoc("doc1")
				.build();
		AvscBaseTypeGenerator field2 = AvscBaseTypeGenerator.getBuilder()
				.setAliases(Arrays.asList("old_field2", "old2_field2"))
				.setName("field2")
				.setNullable()
				.setDefaultValue("default2")
				.setType(AvroTypes.STRING)
				.build();
		AvscBaseTypeGenerator field3 = AvscBaseTypeGenerator.getBuilder()
				.setAliases(Arrays.asList("odl_field3"))
				.setName("field3")
				.setOptional()
				.setDefaultValue("should not take effect because optional=true so default is null")
				.setType(AvroTypes.STRING)
				.build();
		
		AvscBaseTypeGenerator field4 = AvscBaseTypeGenerator.getBuilder()
				.setAliases(Arrays.asList("odl_field4"))
				.setName("field4")
				.setOptional()
				.setDefaultValue(Integer.valueOf(1))
				.setType(AvroTypes.INTEGER)
				.build();
		
		AvscBaseTypeGenerator field5 = AvscBaseTypeGenerator.getBuilder()
				.setAliases(Arrays.asList("odl_field5"))
				.setName("field5")
				.setOptional()
				.setDefaultValue(Boolean.valueOf(false))
				.setType(AvroTypes.BOOLEAN)
				.build();		
		
		AvscComplexTypeGenerator complexRecord2 = AvscComplexTypeGenerator.getBuilder()
					.setAlias(Arrays.asList("old_record2", "odl2_record2"))
					.setDoc("example doc")
					.setName("record2")
					.setNamespace("com.events.avro")
					.setType(AvroTypes.RECORD)
					.addChildren(Arrays.asList(field3, field4))
					.build();
		
//		AvscComplexTypeGenerator enum = AvscComplexTypeGenerator.getBuilder()
//		 .setAlias(Arrays.asList("old_enum"))
//		 .setDoc("this is an enum")
//		 .setName("enum1")
//		 .setNamespace("com.events.avro")
//		 .addChildren(Arrays.asList("A", "B", "C"))
//		 .build();
		 
		
		AvscComplexTypeGenerator complexRecord1 = AvscComplexTypeGenerator.getBuilder()
			.setAlias(Arrays.asList("old_record1"))
			.setDoc("example doc")
			.setName("record1")
			.setNamespace("com.events.avro")
			.setType(AvroTypes.RECORD)
			.addChildren(Arrays.asList(complexRecord2,field1,field2,field5))
			.build();

		
		System.out.println(complexRecord1.generate().toString());
		
		
	}
}
