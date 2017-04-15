/**
 *
 */
package com.slidetorial.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author goobar
 *
 */
@Transactional
@Service
public class TransactionalServiceB
{
	private final ExampleEntityRepository entityRepository;

	@Autowired
	public TransactionalServiceB(ExampleEntityRepository entityRepository)
	{
		this.entityRepository = entityRepository;
	}

	public void updateEntity(long id, UpdateEntityCommand command)
	{
		entityRepository.save(UpdatedEntityBuilder.buildUpdatedEntity(
			entityRepository.findOne(id), command));
	}
}
