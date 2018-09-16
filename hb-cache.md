# Hibernate Cache

## First Level Cache

### By Session object

When we update an persistent object, then we update it again,This does not necessarily 
result into two queries. Hibernate intelligently detects what is the minimum number
of queries to be run and based on that it triggers the update query.

Even for the SELECT, suppose we do a SELECT once and there is no change
in the object as such and I do another SELECT by session.get then HB knows that
It already has data in cache so it does not fire the SELECT query second time.

## Second Level cache

### What is the need of second Level Cache

Session is not something which we would hold data for long period of time.
As we know SessionFactory is there with entire life cycle of the application.
But Session object we open only when we need to talk to the DB and then close 
it when we done talking to the DB. So we w not be able to access cache once
session is closed.
So we need to have cache available beyond the session.

### Ways to implement second Level of cache

- Across sessions in an application
- Across applications
- Across clusters

### Configuring EH cache as second Level Cache provider

* include EH cache Jar
* Annotate Entity classed to use caching
* Cache Provider class

```java
<property name="cahce.provider_class">org.hibernate.cache.EhCacheProvider</property>
<property name="cache.use_second_level">true</property>

```

```java
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
```

## 3. Using Query Cache

We need to specify caching of query results separately.

* specify query as Cacheable `query.setCacheable(true)`

```java
<property name="cache.use_query_level">true</property>
```