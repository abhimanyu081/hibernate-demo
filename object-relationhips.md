# Object Relations in Hibernate

## One To One Mapping(vid-13)

```java
@OneToOne
@JoinColumn(name="VEHICLE_ID")
private Vehicle vehicle;

```

```java
@Entity
public class Vehicle {
   @Id
   @GeneratedValue
   private int vehicleId;
   private String vehicleName;
}
```

When we put @oneToOne annotations on Vehicle HB creates a column for vehicle_id in UserDetails Table.

USER_ID | USER_NAME | VEHICLE _ID
---------|----------|---------
 1001 | user1 | 2
 1002 | user2 | 3
 1003 | user3 | 4

VEHICLE_ID | VEHICLE_NAME
---------|----------
 1 | Car

 In this case HB executes following queries.

```sql
insert into USER_DETAILS (userName, VEHICLE_ID, userId) values (?, ?, ?)
insert into Vehicle (vehicleName, vehicleId) values (?, ?)
update USER_DETAILS set userName=?, VEHICLE_ID=? where userId=?
```

1. Insert User row without vehicle Id.
2. Insert Vehicle and generate vehicle Id.
3. Update User table with vehicle_id.

## One To Many Mapping(14)

Case 1:- Without Specifying reverse relationship.

When we use @OneToMany annnotations instead of creating a join column, HB creates a mapping table of userId --> Vehicle_Id. To overcome this problem we use **@JoinTable**.

```java
@OneToMany
private Collection<Vehicle> vehicle = new ArrayList<>();
```

Tables after this user object Insetion.

USER_DETAILS

USER_ID | USER_NAME
---------|---------
 1 | USER1

VEHICLE

VEHICLE_ID | VEHICLE_NAME
---------|----------
 2 | Audi
 3 | BMW
 4 | FERRARI

USER_VEHICLE_MAPPING

USER_ID | VEHICLE_ID
---------|----------
 1 | 2
 1 | 3
 1 | 4

This is not what we would want. We would instead prefer a column for vehicle_id.
We can configure the column names of the mapping table
using @JoinTable.

```java
@OneToMany
@JoinTable(
   name="USER_VEHICLE_MAPPING",
   joinColumns=@JoinColumn(name="USER_ID"),
   inverseJoinColumns=@JoinColumn(name="VEHICLE_ID")
)
private Collection<Vehicle> vehicle = new ArrayList<>();
```

HB executes following set of queries for the @OneToMany save object.

```sql
insert into USER_DETAILS (USER_NAME, USER_ID) values (?, ?)
insert into VEHICLE (VEHICLE_NAME, VEHICLE_ID) values (?, ?)
insert into VEHICLE (VEHICLE_NAME, VEHICLE_ID) values (?, ?)
insert into USER_VEHICLE_MAPPING (USER_ID, VEHICLE_ID) values (?, ?)
insert into USER_VEHICLE_MAPPING (USER_ID, VEHICLE_ID) values (?, ?)
```

## @JoinTable

* **JoinColumns** - Column name for current Entity e.g USER_ID of USER_DETAILS in USER_VEHICLE_MAPPING
* **inverseJoinColumns** - Column name for the embedded object e.g VEHICLE_ID of Vehicle in USER_VEHICLE_MAPPING

## Reverse Relatioship

**What is the purpose of having reverse relationship?**

When we use @OneToMany relatioship in an Entity, we can have a reverse relatioship (i.e @ManyToOne) in the Embedded object so that we can get the owner of the Embedded object when we need it. e.g In Vehicle we have reverse relatioship of UserDetails table so that we can get the owner of the vehicle.

```java
@Entity(name = "USER_DETAILS")
public class UserDetails {
     @OneToMany
     @JoinTable(
        name="USER_VEHICLE_MAPPING",
        joinColumns=@JoinColumn(name="USER_ID"), 
        inverseJoinColumns=@JoinColumn(name="VEHICLE_ID")
)
private Collection<Vehicle> vehicle = new ArrayList<>();

}
```

```java
@Entity
@Table(name="VEHICLE")
public class Vehicle {

 @Id
 @GeneratedValue
 @Column(name="VEHICLE_ID")
 private int vehicleId;

 @Column(name="VEHICLE_NAME")
 private String vehicleName;

 @ManyToOne
 private UserDetails user;

}
```

# mappedBy and Many To Many Mapping (15)

There is one other way to represent @OneToMany and @ManyToOne relationship.
The object on the 'Many' side of relatioship has reference of the other object.
Now User table can not have column for the vehicle_id in USER_DETAILS table as there can be many vehicles for one user but vehicle table can have user_id column because any vehicle will have only one user, its a many to one relatioship as far as the vehicle side of the relatioship is concerned.
So we can as well have a user_id column in the Vehicle Table instead of having a seperate table for USER_ID --> VEHICLE_ID mapping.

mappedBy - Where you want the mapping to be happened.
We want the mapping to be happended in the vehicle table i.e. vehicle table will have a USER_ID column.

```java
@Entity(name = "USER_DETAILS")
public class UserDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)

@Column(name="USER_ID")
private Integer userId;

@Column(name="USER_NAME")
private String userName;

@OneToMany(mappedBy="user")
private Collection<Vehicle> vehicle = new ArrayList<>();
}

```

```java
@Entity
@Table(name="VEHICLE")
public class Vehicle {

@Id
@GeneratedValue
@Column(name="VEHICLE_ID")
private int vehicleId;

@Column(name="VEHICLE_NAME")
private String vehicleName;

@ManyToOne
@JoinColumn(name="USER_ID")
private UserDetails user;

}

```

When we save the user Object follwing queries executed.

```sql
insert into USER_DETAILS (USER_NAME, USER_ID) values (?, ?)
insert into VEHICLE (USER_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
insert into VEHICLE (USER_ID, VEHICLE_NAME, VEHICLE_ID) values (?, ?, ?)
```

@OneToMany(mappedBy="user") :- "user" is property in Vehicle
@JoinColumn(name="USER_ID") :- USER_ID column contains id from USER_DETAILS

## ManyToMany relatioship

To use many to many relatiohsip we need to put @ManyToMany annotations on both sides of the Object.

```java
@ManyToMany
private Collection<RentedVehicle> vehiclesList = new ArrayList<>();

@ManyToMany
private Collection<UserDetails> userList = new ArrayList<UserDetails>();

session.save(user);
session.save(v1);
session.save(v2);
```

We we save these objects HB creates two mapping tables 

RENTED_VEHICLE_USER_DETAILS

RentedVehicle_VEHICLE_ID | userList_USER_ID
---------|----------
 3 | 1
 3 | 1

USER_DETAILS_RENTED_VEHICLE

USER_DETAILS_USER_ID | vehiclesList_VEHICLE_ID
---------|----------
 1 | 2
 1 | 3
one of which is redundant. We need only one mapping table.

```java
@ManyToMany(mappedBy="vehiclesList")
private Collection<UserDetails> userList = new ArrayList<UserDetails>();

@ManyToMany
private Collection<RentedVehicle> vehiclesList = new ArrayList<>();

```

In the above case mapping is done by only one @ManyToMany, other annotation only tells the HB that mapping has already been done in another class by specifying mappedBy.