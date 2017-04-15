/**
 *
 */
package com.slidetorial.example;

/**
 * @author goobar
 *
 */
public class UpdatedEntityBuilder
{
	public static ExampleEntity buildUpdatedEntity(
		ExampleEntity oldManagedEntity, UpdateEntityCommand command)
	{
		// According to official Hibernate documentation, version field
		// of managed entity (returned by repository) cannot be modified
		// manually - see
		// http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#_optimistic
		// Therefore, we cannot simply set version field on oldEntity.
		// The only way to follow the official documentation and have
		// the advantages of optimistic locking is to create unmanaged
		// updated entity and persist it as a new entity
		ExampleEntity updatedEntity = new ExampleEntity(
			oldManagedEntity);
		updatedEntity.setVersion(command.getVersion());
		command.getAnotherProperty()
			.ifPresent(updatedEntity::setAnotherProperty);
		command.getName().ifPresent(updatedEntity::setName);
		return updatedEntity;
	}
}
