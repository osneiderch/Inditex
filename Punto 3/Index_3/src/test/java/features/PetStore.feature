Feature: PetStore API Tests
  Scenario: Crear un usuario y recuperar sus datos
    Given url 'https://petstore.swagger.io/v2/user'
    And request { "id":1147, "username": "OznE", "firstName": "Osneider", "lastName": "Carreno", "email": "osneider@gmail.com", "password": "Taganga", "phone": "613563627", "userStatus": 1 }
    When method post
    Then status 200

    Given url 'https://petstore.swagger.io/v2/user/OznE'
    When method get
    Then status 200
    And def response = response
    And print response
    And match response.id == 1147

  Scenario: Listar mascotas vendidas
    Given url 'https://petstore.swagger.io/v2/pet/findByStatus?status=sold'
    When method get
    Then status 200
    And def pets = response
    And def soldPets = pets.filter(p => p.status == 'sold').map(p => ({ id: p.id, name: p.name }))
    * print soldPets
