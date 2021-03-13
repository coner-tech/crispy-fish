# Changelog

## 0.3.0 (unreleased)

- Overhauled Registration handling
  - All columns in .rgg file are now available in Registration models
  - Most Registration model properties are now nullable
    - Previously empty strings will now be null
    - Improves reliability of reading registration for starting and running events
  - Custom property map
  - Removed `RegistrationMapper` and other internal classes from the public API
    - Use `EventControlFile.query*()` methods to query registrations, results, etc
  - Registrations will not have results if they lack a position, such as DNS

## 0.2.3

- Upgraded dependencies
  - Kotlin 1.4
  - JVM Target 11

## 0.2.2

- Expanded Registration reading to include:
    - Best Run 

## 0.2.1

- Expanded Registration reading to include:
    - Member Number
    - Individual run times/penalties
    - Overall raw/pax times and standings
- Changed queries to source data cached in registration file

## 0.2.0

- New queries:
  - Class definitions
    - Categories
    - Handicaps
  - Registrations

## 0.1.1

- Clean up registrations file API and implementation

## 0.1.0

Initial release.

Features:
- Registrations
    - Locate correct file
    - Read registration records with basic information
- Query raw and pax times from staging files
