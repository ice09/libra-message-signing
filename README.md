# Libra Message Signer and Verifier (jlibra extension) 

Implements a message signing and verification mechanism using Libra accounts (ie. associated key pairs).  
Can be used to check if a message was signed by a certain Libra address to prove ownage of this address. 

### Prerequisites

* Java 8+
* Maven 3+ (optional)

### Use

To use signing and verifying messages out of the scope of custom Java applications, `libra-java-client` can be used.

### Run

Run the downloaded binary with this command in a shell:
```
java -jar java-libra-client-0.0-alpha-1.jar
```

The application should start with a jlibra splash screen and a shell.  

```
       _ ___ __
      (_) (_) /_  _________ _
     / / / / __ \/ ___/ __ `/    .: Connecting Libra to Java :.
    / / / / /_/ / /  / /_/ /
 __/ /_/_/_.___/_/   \__,_/
/___/

jlibra:>a c
jlibra:>a c
jlibra:>s m "hello" 0
jlibra:>v m "hello" ... 0

```

### Real Life Use Case

