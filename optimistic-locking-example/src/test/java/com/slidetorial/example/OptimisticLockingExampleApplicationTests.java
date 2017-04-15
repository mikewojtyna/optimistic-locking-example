package com.slidetorial.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
public class OptimisticLockingExampleApplicationTests
{

	@Autowired
	private ExampleEntityRepository repository;

	@Autowired
	private TransactionalServiceA serviceA;

	@Autowired
	private TransactionalServiceB serviceB;

	/**
	 * The first scenario - update command with stale version number fails
	 * to update the entity after it's updated by earlier transaction.
	 *
	 * @throws Exception
	 */
	@Test
	public void should_ThrowOptimisticLockingFailureException_When_UpdateCommandHasStaleVersionNumber()
		throws Exception
	{
		// given
		ExampleEntity entity = new ExampleEntity("property0", "name0");
		// this entity is managed by JPA provider
		// version is set to 0 by the db
		ExampleEntity existingEntity = repository.save(entity);
		assertEquals(0, existingEntity.getVersion());

		// when
		// first service updates the entity by changing its name in a
		// transaction...
		serviceA.updateEntity(existingEntity.getId(),
			new UpdateEntityCommand.Builder(
				existingEntity.getVersion()).withName("name1")
					.build());
		// ... now, the entity is updated and first transaction
		// is over
		// version is set to 1 in the db (automatically updated by JPA
		// provider) after the update

		// then
		// this call should throw optimistic locking exception, because
		// it updates the entity with version 1 in the db, but
		// existingEntity object has the old version value set to 0
		// (managed entities are not back-linked to the original
		// unmanaged entity, so version is not updated on the unmanaged
		// entity)
		try
		{
			serviceB.updateEntity(existingEntity.getId(),
				new UpdateEntityCommand.Builder(
					existingEntity.getVersion())
						.withAnotherProperty(
							"property1")
						.build());
			fail("Expected exception is not thrown");
		}
		catch (OptimisticLockingFailureException e)
		{
			// optimistic exception is thrown - test passed
			e.printStackTrace();
		}
	}

	/**
	 * The second scenario - update command succeeds to update the entity,
	 * because the command has current (not stale) version number.
	 *
	 * @throws Exception
	 */
	@Test
	public void should_UpdateEntity_When_UpdateCommandHasCurrentVersionNumber()
		throws Exception
	{
		// given
		ExampleEntity entity = new ExampleEntity("property0", "name0");
		// this entity is managed by JPA provider
		// version is set to 0 by the db
		ExampleEntity existingEntity = repository.save(entity);
		assertEquals(0, existingEntity.getVersion());

		// when
		// first service updates the entity by changing its name in a
		// transaction...
		serviceA.updateEntity(existingEntity.getId(),
			new UpdateEntityCommand.Builder(
				existingEntity.getVersion()).withName("name1")
					.build());
		// ... now, the entity is updated and first transaction
		// is over
		// version is set to 1 in the db (automatically updated by JPA
		// provider) after the update

		// then
		// this call succeeds, because version number is increased by 1
		// in the command
		long version = existingEntity.getVersion() + 1;
		serviceB.updateEntity(existingEntity.getId(),
			new UpdateEntityCommand.Builder(version)
				.withAnotherProperty("property1").build());
	}

	/**
	 * The third scenario - the same scenario as first one, except the test
	 * method is running in a transaction. This means that both service
	 * transactions are part of the bigger transaction started by the test
	 * method. Optimistic locking exception is not thrown, because
	 * everything is running as a single transaction. There is just a single
	 * write to the db, so it's impossible to cause a conflict.
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void should_UpdateEntity_When_UpdateCommandHasStaleVersionNumber_And_TestIsRunningInTransaction()
		throws Exception
	{
		// given
		ExampleEntity entity = new ExampleEntity("property0", "name0");
		// this entity is managed by JPA provider
		// version is set to 0 by the db
		ExampleEntity existingEntity = repository.save(entity);
		assertEquals(0, existingEntity.getVersion());

		// when
		// first service updates the entity by changing its name in a
		// transaction...
		serviceA.updateEntity(existingEntity.getId(),
			new UpdateEntityCommand.Builder(
				existingEntity.getVersion()).withName("name1")
					.build());
		// ... now, the entity is updated and first transaction
		// is over
		// version is set to 1 in the db (automatically updated by JPA
		// provider) after the update

		// then
		// The command has stale version number (0), but exception is
		// not thrown. The reason is because there's only a single write
		// to the db. This test is annotated with @Transactional, so
		// it's running as a single transaction and (service
		// transactions are merged)
		serviceB.updateEntity(existingEntity.getId(),
			new UpdateEntityCommand.Builder(
				existingEntity.getVersion())
					.withAnotherProperty("property1")
					.build());
	}

}
