# noobmaster-fixlength
- Java library about fixed length string.
- Able to format object's fields to fixed length string.
- Able to parse fixed length string to object.
- Able to customize logic how to format and parse value.

### Out of the box annotation

- @FixLength
  - position: position of field start from 1, 2, 3, ...
  - length: fixed length value
  - padChar: padding character if value length less than fixed length
  - align: value alignment to LEFT or RIGHT
  - adapter: adapter class how to format and parsed value
- @FixLengthIgnore
  - value: list of ignore position, the adapter will skip to format and parse that positions 
- @FixLengthBooleanFormat
  - trueValue: string value of TRUE
  - falseValue: string value of FALSE
  - caseSensitive
- @FixLengthNumberFormat
  - pattern
- @FixLengthDateFormat
  - pattern
  - locale

> If using custom adapter the these annotation will ignore
> - @FixLengthBooleanFormat
> - @FixLengthNumberFormat
> - @FixLengthDateFormat

### Supported field types by default

- Text
  - java.lang.String
  - java.lang.Character
- Number
  - java.lang.Byte
  - java.lang.Short
  - java.lang.Integer
  - java.lang.Long
  - java.lang.Float
  - java.lang.Double
  - java.math.BigInteger
  - java.math.BigDecimal
- Date and Time
  - java.util.Date
  - java.sql.Date
  - java.sql.Time
  - java.sql.Timestamp
  - java.time.LocalDate
  - java.time.LocalTime
  - java.time.LocalDateTime
- Logical
  - java.lang.Boolean

### Example

```java
class Employee {
    @FixLength(position = 1, length = 20)
    private String name;

    @FixLength(position = 2, length = 2, align = Align.RIGHT, padChar = '0')
    private Integer workExperience;

    @FixLength(position = 3, length = 8)
    @FixLengthDateFormat(pattern = "yyyyMMdd", locale = "en_US")
    private LocalDate dateOfBirth;
    
    // ...
}

class Test {

    public static void main(String[] args) {
        FixLengthMapper<Employee> mapper = new FixLengthMapper<>(Employee.class);
        Employee employee = new Employee();
        employee.setName("Noob Master");
        employee.setWorkExperience(9);
        employee.setDateOfBirth(LocalDate.of(1900, 1, 31));

        String fixedLengthString = mapper.format(employee);
        System.out.println(fixedLengthString);

        Employee asuna = mapper.parse("Yuki Asuna          152007/09/30");
        System.out.println(asuna);
    }
}
```
The Result is:
```
Noob Master         0919000131
Employee{name='Yuki Asuna', workExperience=15, dateOfBirth=2007-09-30}
```
