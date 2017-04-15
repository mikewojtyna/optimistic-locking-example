/**
 *
 */
package com.slidetorial.example;

import java.util.Optional;

/**
 * @author goobar
 *
 */
public class UpdateEntityCommand
{
	private final Optional<String> anotherProperty;

	private final Optional<String> name;

	private final long version;

	private UpdateEntityCommand(Builder builder)
	{
		anotherProperty = builder.anotherProperty;
		name = builder.name;
		version = builder.version;
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
		UpdateEntityCommand other = (UpdateEntityCommand) obj;
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
	public Optional<String> getAnotherProperty()
	{
		return anotherProperty;
	}

	/**
	 * @return the name
	 */
	public Optional<String> getName()
	{
		return name;
	}

	/**
	 * @return
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
		result = prime * result
			+ ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (version ^ (version >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UpdateEntityCommand [anotherProperty=" + anotherProperty
			+ ", name=" + name + ", version=" + version + "]";
	}

	public static class Builder
	{

		private Optional<String> anotherProperty = Optional.empty();

		private Optional<String> name = Optional.empty();

		private final long version;

		public Builder(long version)
		{
			this.version = version;
		}

		public UpdateEntityCommand build()
		{
			return new UpdateEntityCommand(this);
		}

		public Builder withAnotherProperty(String anotherProperty)
		{
			this.anotherProperty = Optional.of(anotherProperty);
			return this;
		}

		public Builder withName(String name)
		{
			this.name = Optional.of(name);
			return this;
		}
	}

}
