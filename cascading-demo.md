# Cascading

Aplicable to all type of relationships.

## Q What does cascade mean

Lets take an example. Suppose A user has 10 vehicles, then while saving a user object, we will have to save all vehicle objects also, like below.
If we do not save the dependent child object then an exception will be thrown.

```
session.save(user);
session.save(vehicle1);
session.save(vehicle1);
session.save(vehicle1);
.
.
.
session.save(vehiclen);

```

So every operation we do with the user object we will also have to do it with all vehicles.

## Save vs persist

CascadeType works only with persist method.
