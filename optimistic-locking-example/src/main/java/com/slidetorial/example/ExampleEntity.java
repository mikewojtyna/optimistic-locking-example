/**
 *
 */
package com.slidetorial.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author goobar
 *
 */
@Entity
public class ExampleEntity
{
	private String anotherProperty;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Version
	private long version;

	public ExampleEntity()
	{
		super();
	}

	public ExampleEntity(ExampleEntity exampleEntity)
	{
		anotherProperty = exampleEntity.anotherProperty;
		id = exampleEntity.id;
		name = exampleEntity.name;
		version = exampleEntity.version;
	}

	public ExampleEntity(String anotherProperty, Long id, String name,
		long version)
	{
		this.anotherProperty = anotherProperty;
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public ExampleEntity(String anotherProperty, String name)
	{
		this.anotherProperty = anotherProperty;
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		ExampleEntity other = (ExampleEntity) obj;
		if (anotherProperty == null)
		{
			if (other.anotherProperty != null)
			{
				return false;
			}
		}
		else if (!anotherProperty.equals(other.anotherProperty))
		{
			return false;
		}
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (version != other.version)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the anotherProperty
	 */
	public String getAnotherProperty()
	{
		return anotherProperty;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the version
	 */
	public long getVersion()
	{
		return version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anotherProperty == null) ? 0
			: anotherProperty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
			+ ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (version ^ (version >>> 32));
		return result;
	}

	/**
	 * @param anotherProperty
	 *                the anotherProperty to set
	 */
	public void setAnotherProperty(String anotherProperty)
	{
		this.anotherProperty = anotherProperty;
	}

	/**
	 * @param id
	 *                the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @param name
	 *                the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param version
	 *                the version to set
	 */
	public void setVersion(long version)
	{
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ExampleEntity [anotherProperty=" + anotherProperty
			+ ", id=" + id + ", name=" + name + ", version="
			+ version + "]";
	}

}
