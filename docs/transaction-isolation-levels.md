# Transaction isolation Levels

Transaction isolation levels are a measure to which transaction isolation succeeds. Specifically this is a measure of presence or absence of any the following properties.

**Dirty Reads:** A *dirty read* happens when a transaction reads a row that has not yet been committed.For example suppose transaction 1 updates a row, and transaction 2 reads the updated row before transaction 1 commits the row. If transaction 1 rolls back the change, transaction 2 will still have the updated data which is considered absent in the database. 

**Non repeatable Reads:** A *non repeatable read* occurs when a transaction reads the same row twice and gets different results each time. For example transaction 1 reads a row from the database, transaction 2 updates/deletes the row and commits the update/delete. Transaction 1 tries to re-read the same row, it gets updated row or fids that the row has been deleted.

**Phantoms:** A *phantom* is a row that matches some search criteria which is not initially discovered. For example suppose transaction 1 executes a query which returns a set of rows. Transaction 2 inserts/updates a row that matches the search criteria of transaction 1. If transaction 1 re-executes the same query it gets a different set of rows.


Four transaction isolation levels based on this above criteria are as follows:

Transaction isolation level | Dirty reads	| Nonrepeatable reads | Phantoms |
--- | --- | --- | ---
Read uncommitted |X|X|X
Read committed|--|X|X
Repeatable read	|--|--|X
Serializable|--|--|--


Syntax:
  
`SET TRANSACTION ISOLATION LEVEL
    { READ UNCOMMITTED
    | READ COMMITTED
    | REPEATABLE READ
    | SNAPSHOT
    | SERIALIZABLE
    } 
    BEGIN TRANSACTION
    {--SQL--}
    COMMIT TRANSACTION`
 
Transaction isolation | Implementation | Uses
---|---|---
READ UNCOMMITTED | No shared lock to prevent other transactions from modifying the data while this transaction is active. It allows *dirty reads*, values in the row can appear or disappear in the dataset while this transaction is active. Least restrictive of all transaction levels.| Big aggregate reports showing constantly changing data
READ COMMITTED | Specifies that statements cannot read rows that has been written but not yet committed by the other transaction. This prevents *dirty reads*. Data can be changed in between by other transactions between individual statements in the current transaction, resulting in *non-repeatable read* or *phantom reads* | 
REPEATABLE READ | Specifies that statements cannot read rows that has been updated but not yet been committed and that no other transaction can modify data that has already been read by the current transaction until it completes. Shared locks are placed on all data read by each statement until the transaction completes. Other transactions can insert rows matching the current search criteria. If current statement retries the same query again it will be able to fetch the new rows resulting in *phantom read*|
SNAPSHOT| Specifies that data read in a transaction will be a consistent version of the data that existed at the start of a transaction. Modifications made by other transactions will not be visible | 
SERIALIZABLE | Enforces statements not to read data that has been modified but ot yet committed by other transactions. No other transaction can modify data that has been read by the transaction until it completes. No other transaction can insert data that would fall under the search criteria of the current transaction. | Used primarily for real time transactions where there is a possibility of race conditions.
