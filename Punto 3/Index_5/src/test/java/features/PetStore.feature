Feature: PetStore API Tests

  Scenario: Contar mascotas vendidas por nombre
    Given url 'https://petstore.swagger.io/v2/pet/findByStatus?status=sold'
    When method get
    Then status 200
    And def pets = response
    And def soldPets = pets.filter(p => p.status == 'sold').map(p => ({ id: p.id, name: p.name }))
    * print soldPets
    And match soldPets != null