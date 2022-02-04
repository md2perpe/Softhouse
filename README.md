# README

This repository was created to host
the task I was given by [Softhouse](https://www.softhouse.se/)
as part of a recruitment process.


## Usage

```
java -jar softhouse.jag <infile> <outfile>
```

## Task

Convert from a row-based file format to an XML format.

## Input file format

There are four types of rows:
```
P|firstname|lastname
T|mobile|landline
A|street|city|zipcode
F|name|birthyear
```

### Example

```
P|Carl Gustaf|Bernadotte
T|0768-101801|08-101801
A|Drottningholms slott|Stockholm|10001
F|Victoria|1977
A|Haga Slott|Stockholm|10002
F|Carl Philip|1979
T|0768-101802|08-101802
P|Barack|Obama
A|1600 Pennsylvania Avenue|Washington, D.C
```

## Output file format

Here is the main structure:
```xml
<people>
  <person>
    <firstname>...</firstname>
    <lastname>...</lastname>
    <address>
      <street>...</street>
      <city>...</city>
      <zipcode>...</zipcode>
    </address>
    <phone>
      <mobile>...</mobile>
      <landline>...</landline>
    </phone>
    <family>
      <name>...</name>
      <born>...</born>
      <address>...</address>
    </family>
  </person>
</people>
```
There can be many `<person>` elements and many `<family>` elements for each person.

### Example
```xml
<people>
  <person>
    <firstname>Carl Gustaf</firstname>
    <lastname>Bernadotte</lastname>
    <address>
      <street>Drottningholms slott</street>
      ...
     </address>
    <phone>
      <mobile>0768-101801</mobile>
      ...
    </phone>
    <family>
      <name>Victoria</name>
        <born>1977</born>
        <address>...</address>
     </family>
     <family>...</family>
  </person>
  <person>...</person>
</people>
```
