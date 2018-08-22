# jenaRDF
Simpla application using Apache Jena to manage RDF data. The application is in the branch called RDF.

# More about Apache Jena, RDF, TDB, and Fuseki
https://jena.apache.org/documentation/rdf/index.html
https://jena.apache.org/documentation/tdb/index.html
https://jena.apache.org/documentation/fuseki2/index.html

# Requirements
Apache Jena java library 3.7 or higher (https://jena.apache.org/download/index.cgi)
Apache Jena fuseki 3.7 or higher (Only for menu items 6 and 7 https://jena.apache.org/download/index.cgi)

# Introduction
This simple applications manages the CRUD of an user resource, which contains the name and email attributes. The user can also have a relationship with other user by means of a know attribute. All these attributes are bound to the Friend Of A Friend (FOAF) vocabulary.

# Application menu

1 - Create User  

This option allows the creation of users by registering its name and email.

2 - Read Users  

This option reads all users in plain text format, querying them using a SPARQL query and returning the results.

3 - Read Users in Specific Format  

Print users in specific format (RDF/XML, JSON-LD, TEXT/TTL)

4 - Add know relationships  

Allows the relationship between users, using the FOAF know property.

5 - Delete resources  

Allows user exclusion.

6 - Upload RDF/XML to Fuseki  

Upload the RDF model to an apache Fuseki server.

7 - Get RDF/XML from Fuseki  

Retrieve a RDF model from an apache Fuseki server.

8 - Query by name from Fuseki  

Get an input name and build a SPARQL query that is sent to an apache Fuseki server, which returns the query result.

9 - Store model in TDB  

Store a RDF model in a Triple Store Database (TDB).

10 - Retrieve model from TDB  

Retrieve a RDF model from a Triple Store Database (TDB).
