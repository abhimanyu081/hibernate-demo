# Cascading and other things.

## **@NotFound**

It is a hibernate feature (not available in JPA) that is used when entities are associated with each other by `@ManyToOne` , `@OneToMany` etc.When the joined Object has no data then HB throws an exception. To avoid this Exception we use `@NotFound` which has two options available `NotFoundAction.IGNORE` and `NotFoundAction.EXCEPTION`.

When dealing with associations which are not enforced by a Foreign Key, it’s possible to bump into inconsistencies if the child record cannot reference a parent entity.

By default, Hibernate will complain whenever a child association references a non-existing parent record. However, you can configure this behavior so that Hibernate can ignore such an Exception and simply assign null as a parent object referenced.

To ignore non-existing parent entity references, even though not really recommended, it’s possible to use the annotation `org.hibernate.annotation.NotFound annotation` with a value of `org.hibernate.annotations.NotFoundAction.IGNORE`.

Explore more.

```java
@Entity
public class UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String state;
    private String pinCode;
    private String country;

    @ManyToOne
    @NotFound(action = NotFoundAction.EXCEPTION)
    private UserDetails user;

}
```