
Where:
- `YYMMDD` – Date of Birth (e.g. `900101` = 1 Jan 1990)
- `SSSS` – Gender sequence:
    - `0000–4999` = Female
    - `5000–9999` = Male
- `C` – Citizenship:
    - `0` = SA Citizen
    - `1` = Permanent Resident
- `A` – Usually 8 or 9 (not validated here)
- `Z` – Luhn check digit

---

## ✅ Validation Checks

### Implemented in `ValidateSaId.java`:
- 📏 **Length & numeric check**: Must be 13 digits.
- 📅 **Valid date check**: `YYMMDD` must represent a real date.
- 👤 **Gender code check**: Digits 7–10 must be in range `0000–9999`.
- 🪪 **Citizenship digit**: Only `0` or `1` allowed.
- 🔢 **Luhn checksum**: Final digit must satisfy the Luhn algorithm.

---

## 🧪 Unit Tests (`ValidateSaIdTest.java`)

Includes comprehensive tests to verify:
- ✅ Valid IDs (e.g., `2001014800086`, `2909035800085`)
- ❌ Too short or too long inputs
- ❌ Non-numeric characters
- ❌ Invalid dates (e.g., 31st Feb, month 13)
- ❌ Invalid citizenship digits (must be 0 or 1)
- ❌ Invalid checksum values
- ❌ Null inputs

Sample test method:
```java
@Test
public void shouldReturnTrueForValidIDNumbers() {
    assertTrue(ValidateSaId.isIdNumberValid("2001014800086"));
}
