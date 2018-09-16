# named query

* Allows writing queries on entity level.
* Refer anywhere in the App.
* Can be used Native SQL (NamedNativeQuery).

```java
@NamedQuery(name="UserDetailsByID", query="from UserDetails where ID =?")
@NamedNativeQuery(name="UserDetails.byName",query="select * from users_detais where username = ?",resultClass=UserDetails.class)

```