# ACID

A transaction is a collection of instructions. To maintain the integrity of the database, all transactions must follow ACID properties.

*ACID* - **A**tomicity, **C**onsistency, **I**solation, **D**urability.

Systems that do not meet the ACID compliance are called **BASE** Basically Available, Soft state and Eventually Consistent.
 
- Atomicity : A transaction is an atomic unit, that is either all the operations will succeed or none of the operations
inside the transaction will succeed. 
For example suppose client wants to make multiple writes, unfortunately a fault occurs after some writes 
have been processed like disk is full, network error, some integrity constraint violated. 
If the writes are grouped together in a single atomic transaction and cannot be committed because of the fault, 
the database has to discard all the writes and rollback to the prior state.

- Consistency : Consistency refers to an application specific notion of the database being in a "good state".
The basic idea behind consistency is that there are certain statements (invariants) about the data, that must
always be true. It is the application's responsibility to preserve consistency of the data.
If the application is writing bad data to the database, the database has no way to validate the data.
Only some basic database constraints like foreign key constraint, uniqueness constraint, etc can be checked by the database.

- Isolation : In the sense of ACID properties, isolation means that concurrently executing transactions
are isolated from each other, that cannot interfere with each other. Isolation can be achieved by *serializibility* 
means that each transaction can pretend that it is the only transaction running in the entire database.
The database ensures that when the transactions have committed, the result would have been same if they had
ran serially, even though they might have ran concurrently.
In general *serializable* isolation is rarely used as it has a serious performance penalty. In reality
commercial databases uses a isolation level called *Snapshot isolation*

- Durability : Durability is a promise that data once committed to the database, will not be lost even in the case of a hardware fault.
In single node databases, durability means writing data to the non-volatile memory. It usually involves *write ahead logs* , which
allows recovery in the event of a data structure or hardware failure.








