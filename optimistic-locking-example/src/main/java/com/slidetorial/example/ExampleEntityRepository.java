/**
 *
 */
package com.slidetorial.example;

import org.springframework.data.repository.CrudRepository;

/**
 * @author goobar
 *
 */
public interface ExampleEntityRepository
	extends CrudRepository<ExampleEntity, Long>
{

}
