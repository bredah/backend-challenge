# Backend Challenge

![GitHub Actions](https://github.com/bredah/backend-challenge/workflows/Java%20CI/badge.svg)

[![CircleCI](https://circleci.com/gh/bredah/backend-challenge/tree/master.svg?style=svg)](https://circleci.com/gh/bredah/backend-challenge/tree/master)

## Continuous Integration

The CI pipeline contains the following steps:

- Clone
- Compile
- Test

## Tests

- Albums
  - getAllComments
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByUserId
  - filterByUserId_notFound
  - filterByUserId_invalidValue
  - validateSchema
- Comments
  - getAllComments
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByEmail
  - filterByEmail_notFound
  - filterByEmail_invalidValue
  - validateEmailField_RFC5322
  - validateSchema
- Photos
  - getAllPhotos
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByAlbumId
  - filterByAlbumId_notFound
  - filterByAlbumId_invalidValue
  - filterByUrl
  - filterByUrl_notFound
  - filterByThumbnailUrl
  - filterByThumbnailUrl_notFound
  - validateUrlField
  - validateThumbnailUrlField
  - validateSchema
- Posts
  - getAllPosts
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByUserId
  - filterByUserId_notFound
  - filterByUserId_invalidValue
  - filterByTitle
  - filterByTitle_notFound
  - validateSchema
- Todos
  - getAllTodos
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByUserId
  - filterByUserId_notFound
  - filterByUserId_invalidValue
  - filterByIdAndUserId
  - filterByCompleted_IsTrue
  - filterByCompleted_IsFalse
  - validateSchema
- Users
  - getAllUsers
  - filterById
  - filterById_notFound
  - filterById_invalidValue
  - filterByName
  - filterByName_notFound
  - filterByName_invalidValue
  - filterByUsername
  - filterByUsername_notFound
  - filterByUsername_invalidValue
  - filterByEmail
  - filterByEmail_notFound
  - filterByWebsite
  - filterByWebsite_notFound
  - filterByPhone
  - filterByPhone_notFound
  - ValidateEmailField_RFC5322
  - validateSchema

